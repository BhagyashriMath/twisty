package com.example.twisty.game.logic

import com.example.twisty.data.models.DotPair
import com.example.twisty.data.models.GameState
import com.example.twisty.data.models.GridCoord
import com.example.twisty.data.models.Level
import com.example.twisty.data.models.PathConnection

sealed class GameActionResult {
    data object None : GameActionResult()
    data class DotTouched(val pairId: Int, val colorId: Int) : GameActionResult()
    data class PathExtended(val pairId: Int, val coord: GridCoord) : GameActionResult()
    data class PathRewound(val pairId: Int, val coord: GridCoord) : GameActionResult()
    data class PairConnected(val pairId: Int) : GameActionResult()
    data class InvalidMove(val reason: String) : GameActionResult()
    data class LevelCompleted(val stars: Int, val moves: Int) : GameActionResult()
}

class TwistyGameEngine(private val initialLevel: Level) {

    var state: GameState = GameState(level = initialLevel)
        private set

    fun reset() {
        state = GameState(level = state.level)
    }

    fun loadLevel(level: Level) {
        state = GameState(level = level)
    }

    /**
     * Called when a touch/drag starts on a board cell.
     */
    fun onTouchStart(coord: GridCoord): GameActionResult {
        if (state.isCompleted) return GameActionResult.None
        if (!coord.isInside(state.level.gridWidth, state.level.gridHeight)) return GameActionResult.None

        // 1. Check if the player touched one of the dot endpoints
        val pair = state.level.pairs.find { it.contains(coord) }
        if (pair != null) {
            val newConnections = state.connections.toMutableMap()
            // Reset existing connection for this pair and start fresh from touched dot
            newConnections[pair.pairId] = PathConnection(
                pairId = pair.pairId,
                colorId = pair.colorId,
                coords = listOf(coord),
                isConnected = false
            )
            state = state.copy(
                connections = newConnections,
                activePairId = pair.pairId,
                currentDragCoord = coord
            )
            return GameActionResult.DotTouched(pair.pairId, pair.colorId)
        }

        // 2. Check if the player touched along an existing in-progress or completed path
        val existingPair = state.connections.entries.find { it.value.coords.contains(coord) }
        if (existingPair != null) {
            val pairId = existingPair.key
            val currentCoords = existingPair.value.coords
            val index = currentCoords.indexOf(coord)
            if (index >= 0) {
                // Rewind path up to the touched cell and resume dragging
                val trimmedCoords = currentCoords.subList(0, index + 1)
                val newConnections = state.connections.toMutableMap()
                newConnections[pairId] = existingPair.value.copy(
                    coords = trimmedCoords,
                    isConnected = false
                )
                state = state.copy(
                    connections = newConnections,
                    activePairId = pairId,
                    currentDragCoord = coord
                )
                return GameActionResult.PathRewound(pairId, coord)
            }
        }

        return GameActionResult.None
    }

    /**
     * Called when finger moves into a cell.
     */
    fun onTouchMove(coord: GridCoord): GameActionResult {
        val activePairId = state.activePairId ?: return GameActionResult.None
        val connection = state.connections[activePairId] ?: return GameActionResult.None
        if (connection.isConnected) return GameActionResult.None
        if (!coord.isInside(state.level.gridWidth, state.level.gridHeight)) return GameActionResult.None

        val lastCoord = connection.coords.last()
        if (coord == lastCoord) {
            return GameActionResult.None
        }

        val pair = state.level.pairs.first { it.pairId == activePairId }

        // Backtracking / rewinding check: if moving back onto an already visited cell in this path
        val existingIndex = connection.coords.indexOf(coord)
        if (existingIndex >= 0) {
            val rewoundCoords = connection.coords.subList(0, existingIndex + 1)
            val newConnections = state.connections.toMutableMap()
            newConnections[activePairId] = connection.copy(
                coords = rewoundCoords,
                isConnected = false
            )
            state = state.copy(
                connections = newConnections,
                currentDragCoord = coord
            )
            return GameActionResult.PathRewound(activePairId, coord)
        }

        // Must be orthogonally adjacent to the last coordinate
        if (!lastCoord.isAdjacent(coord)) {
            return GameActionResult.None
        }

        // Obstacle collision check
        if (state.level.obstacles.contains(coord)) {
            return GameActionResult.InvalidMove("Blocked by obstacle")
        }

        // Dot collision check: cannot pass through dots of other pairs
        val otherDot = state.level.pairs.find { it.pairId != activePairId && it.contains(coord) }
        if (otherDot != null) {
            return GameActionResult.InvalidMove("Cannot pass through another dot")
        }

        // Path collision / crossing check: cannot cross other active paths
        for ((otherPairId, otherConn) in state.connections) {
            if (otherPairId != activePairId && otherConn.coords.contains(coord)) {
                return GameActionResult.InvalidMove("Cannot cross existing path")
            }
        }

        // Target dot check: did we reach the matching dot?
        val targetDot = pair.other(connection.coords.first())
        if (coord == targetDot) {
            val completedCoords = connection.coords + coord
            val newConnections = state.connections.toMutableMap()
            newConnections[activePairId] = connection.copy(
                coords = completedCoords,
                isConnected = true
            )
            val newMoves = state.movesCount + 1

            // Check if all pairs in the level are connected
            val allConnected = state.level.pairs.all { p ->
                newConnections[p.pairId]?.isConnected == true
            }

            val stars = if (allConnected) calculateStars(newMoves, state.level) else 0

            state = state.copy(
                connections = newConnections,
                activePairId = null,
                currentDragCoord = null,
                movesCount = newMoves,
                isCompleted = allConnected,
                starsEarned = stars
            )

            return if (allConnected) {
                GameActionResult.LevelCompleted(stars, newMoves)
            } else {
                GameActionResult.PairConnected(activePairId)
            }
        }

        // Normal path extension into an empty cell
        val extendedCoords = connection.coords + coord
        val newConnections = state.connections.toMutableMap()
        newConnections[activePairId] = connection.copy(coords = extendedCoords)
        state = state.copy(
            connections = newConnections,
            currentDragCoord = coord
        )
        return GameActionResult.PathExtended(activePairId, coord)
    }

    /**
     * Called when finger is lifted.
     */
    fun onTouchEnd() {
        val activePairId = state.activePairId ?: return
        val connection = state.connections[activePairId]

        if (connection != null && !connection.isConnected) {
            // Finger was lifted mid-draw; increment move count if at least one step was made
            if (connection.coords.size > 1) {
                state = state.copy(movesCount = state.movesCount + 1)
            }
        }

        state = state.copy(
            activePairId = null,
            currentDragCoord = null
        )
    }

    /**
     * Reveal solution for one uncompleted pair as a hint.
     */
    fun applyHint(): Pair<Int, List<GridCoord>>? {
        if (state.isCompleted) return null

        // Find the first uncompleted pair that has a pre-defined hint path
        val uncompletedPair = state.level.pairs.find { p ->
            state.connections[p.pairId]?.isConnected != true
        } ?: return null

        val hintPath = state.level.hintPaths[uncompletedPair.pairId] ?: return null

        // Remove any conflicting paths from other pairs that intersect this hint path
        val newConnections = state.connections.toMutableMap()
        for ((otherPairId, otherConn) in state.connections) {
            if (otherPairId != uncompletedPair.pairId) {
                if (otherConn.coords.any { hintPath.contains(it) }) {
                    newConnections.remove(otherPairId)
                }
            }
        }

        // Apply hint path
        newConnections[uncompletedPair.pairId] = PathConnection(
            pairId = uncompletedPair.pairId,
            colorId = uncompletedPair.colorId,
            coords = hintPath,
            isConnected = true
        )

        val newMoves = state.movesCount + 1
        val allConnected = state.level.pairs.all { p ->
            newConnections[p.pairId]?.isConnected == true
        }
        val stars = if (allConnected) calculateStars(newMoves, state.level) else 0

        state = state.copy(
            connections = newConnections,
            movesCount = newMoves,
            isCompleted = allConnected,
            starsEarned = stars
        )

        return Pair(uncompletedPair.pairId, hintPath)
    }

    /**
     * Calculate 1-3 stars based on moves and par efficiency.
     */
    private fun calculateStars(moves: Int, level: Level): Int {
        val par = level.pairs.size
        return when {
            moves <= par -> 3           // Flawless one-shot connections
            moves <= par + 2 -> 2       // 1-2 retries
            else -> 1                   // Completed
        }
    }
}
