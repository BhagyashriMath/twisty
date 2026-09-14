package com.example.twisty.data.models

import kotlin.math.abs

/**
 * Represents a discrete coordinate (x, y) on the puzzle board.
 * (0, 0) is top-left.
 */
data class GridCoord(val x: Int, val y: Int) {
    /**
     * Checks if this coordinate is orthogonally adjacent (Up, Down, Left, Right).
     */
    fun isAdjacent(other: GridCoord): Boolean {
        val dx = abs(x - other.x)
        val dy = abs(y - other.y)
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1)
    }

    /**
     * Checks if within grid boundaries.
     */
    fun isInside(gridWidth: Int, gridHeight: Int): Boolean {
        return x in 0 until gridWidth && y in 0 until gridHeight
    }
}
