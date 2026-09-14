package com.example.twisty.data.models

/**
 * Encapsulates the complete live state of a puzzle round.
 */
data class GameState(
    val level: Level,
    val connections: Map<Int, PathConnection> = emptyMap(),
    val activePairId: Int? = null,
    val movesCount: Int = 0,
    val isCompleted: Boolean = false,
    val starsEarned: Int = 0,
    val currentDragCoord: GridCoord? = null
)
