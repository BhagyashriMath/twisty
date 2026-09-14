package com.example.twisty.data.models

/**
 * Represents a drawn or in-progress path for a specific DotPair.
 */
data class PathConnection(
    val pairId: Int,
    val colorId: Int,
    val coords: List<GridCoord>,
    val isConnected: Boolean
)
