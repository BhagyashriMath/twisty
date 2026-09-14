package com.example.twisty.data.models

/**
 * Represents a pair of matching colored nodes to be connected.
 * colorId indexes into the game's color palette (Berry, Tangerine, Lemon, Emerald, Cyan, Violet, etc.)
 */
data class DotPair(
    val pairId: Int,
    val colorId: Int,
    val dotA: GridCoord,
    val dotB: GridCoord
) {
    fun contains(coord: GridCoord): Boolean = coord == dotA || coord == dotB
    fun other(coord: GridCoord): GridCoord? = when (coord) {
        dotA -> dotB
        dotB -> dotA
        else -> null
    }
}
