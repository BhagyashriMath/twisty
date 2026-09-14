package com.example.twisty.data.models

/**
 * Full level definition.
 * @param id Level number (1-30+)
 * @param title Friendly display name (e.g. "Level 1", "Twisty Curves")
 * @param gridWidth Number of columns
 * @param gridHeight Number of rows
 * @param pairs List of colored dot pairs to connect
 * @param obstacles Coordinates of impassable tiles
 * @param moveLimit Optional move limit for extra challenge (0 means unlimited)
 * @param hintPaths Pre-defined solutions for each pair to power the hint system
 */
data class Level(
    val id: Int,
    val title: String,
    val gridWidth: Int,
    val gridHeight: Int,
    val pairs: List<DotPair>,
    val obstacles: Set<GridCoord> = emptySet(),
    val moveLimit: Int = 0,
    val hintPaths: Map<Int, List<GridCoord>> = emptyMap()
)
