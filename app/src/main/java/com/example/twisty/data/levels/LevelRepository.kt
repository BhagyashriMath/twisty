package com.example.twisty.data.levels

import com.example.twisty.data.models.DotPair
import com.example.twisty.data.models.GridCoord
import com.example.twisty.data.models.Level

object LevelRepository {

    val levels: List<Level> by lazy {
        listOf(
            // Level 1 - Sweet Start
            Level(
                id = 1,
                title = "Level 1 - Sweet Start",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 3)),
                    DotPair(2, 4, GridCoord(3, 0), GridCoord(3, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3)),
                    2 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3))
                )
            ),
            // Level 2 - First Bend
            Level(
                id = 2,
                title = "Level 2 - First Bend",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 1, GridCoord(0, 0), GridCoord(3, 0)),
                    DotPair(2, 3, GridCoord(0, 3), GridCoord(3, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0)),
                    2 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3))
                )
            ),
            // Level 3 - Triple Flow
            Level(
                id = 3,
                title = "Level 3 - Triple Flow",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 3)),
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 3)),
                    DotPair(3, 4, GridCoord(2, 0), GridCoord(2, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3))
                )
            ),
            // Level 4 - Twisty Loop
            Level(
                id = 4,
                title = "Level 4 - Twisty Loop",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(3, 1)),
                    DotPair(2, 2, GridCoord(0, 1), GridCoord(2, 1)),
                    DotPair(3, 3, GridCoord(0, 3), GridCoord(3, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(3, 1)),
                    2 to listOf(GridCoord(0, 1), GridCoord(1, 1), GridCoord(2, 1)),
                    3 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3))
                )
            ),
            // Level 5 - Candy Wrap
            Level(
                id = 5,
                title = "Level 5 - Candy Wrap",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(4, 4)),
                    DotPair(2, 4, GridCoord(0, 1), GridCoord(0, 4)),
                    DotPair(3, 1, GridCoord(2, 1), GridCoord(2, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4)),
                    2 to listOf(GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4)),
                    3 to listOf(GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3))
                )
            ),
            // Level 6 - Rainbow Weave
            Level(
                id = 6,
                title = "Level 6 - Rainbow Weave",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 4)),
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 4)),
                    DotPair(3, 2, GridCoord(2, 0), GridCoord(2, 4)),
                    DotPair(4, 3, GridCoord(3, 0), GridCoord(3, 4))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3), GridCoord(2, 4)),
                    4 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4))
                )
            ),
            // Level 7 - Spiral Twist
            Level(
                id = 7,
                title = "Level 7 - Spiral Twist",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(4, 0)),
                    DotPair(2, 4, GridCoord(4, 1), GridCoord(4, 4)),
                    DotPair(3, 1, GridCoord(0, 4), GridCoord(3, 4)),
                    DotPair(4, 3, GridCoord(0, 1), GridCoord(0, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4)),
                    3 to listOf(GridCoord(0, 4), GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4)),
                    4 to listOf(GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3))
                )
            ),
            // Level 8 - Jalebi Swirl
            Level(
                id = 8,
                title = "Level 8 - Jalebi Swirl",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 2), GridCoord(4, 2)),
                    DotPair(2, 2, GridCoord(0, 0), GridCoord(0, 1)),
                    DotPair(3, 4, GridCoord(0, 3), GridCoord(0, 4))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 2), GridCoord(1, 2), GridCoord(2, 2), GridCoord(3, 2), GridCoord(4, 2)),
                    2 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(4, 1), GridCoord(3, 1), GridCoord(2, 1), GridCoord(1, 1), GridCoord(0, 1)),
                    3 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(4, 4), GridCoord(3, 4), GridCoord(2, 4), GridCoord(1, 4), GridCoord(0, 4))
                )
            ),
            // Level 9 - Candy Maze
            Level(
                id = 9,
                title = "Level 9 - Candy Maze",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(2, 2)),
                    DotPair(2, 1, GridCoord(4, 0), GridCoord(4, 4)),
                    DotPair(3, 3, GridCoord(0, 4), GridCoord(2, 3)),
                    DotPair(4, 4, GridCoord(1, 0), GridCoord(3, 0))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(1, 1), GridCoord(2, 1), GridCoord(2, 2)),
                    2 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4)),
                    3 to listOf(GridCoord(0, 4), GridCoord(1, 4), GridCoord(2, 4), GridCoord(2, 3)),
                    4 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0))
                )
            ),
            // Level 10 - Five Twists
            Level(
                id = 10,
                title = "Level 10 - Five Twists",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 4)),
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 4)),
                    DotPair(3, 2, GridCoord(2, 0), GridCoord(2, 4)),
                    DotPair(4, 3, GridCoord(3, 0), GridCoord(3, 4)),
                    DotPair(5, 4, GridCoord(4, 0), GridCoord(4, 4))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3), GridCoord(2, 4)),
                    4 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4)),
                    5 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4))
                )
            ),
            // Level 11 - The Blockade
            Level(
                id = 11,
                title = "Level 11 - The Blockade",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 1), GridCoord(3, 1)),
                    DotPair(2, 4, GridCoord(1, 3), GridCoord(3, 3)),
                    DotPair(3, 1, GridCoord(0, 0), GridCoord(4, 4))
                ),
                obstacles = setOf(GridCoord(2, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1)),
                    2 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3)),
                    3 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4))
                )
            ),
            // Level 12 - Twin Rocks
            Level(
                id = 12,
                title = "Level 12 - Twin Rocks",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(4, 0)),
                    DotPair(2, 3, GridCoord(0, 4), GridCoord(4, 4)),
                    DotPair(3, 1, GridCoord(2, 1), GridCoord(2, 3))
                ),
                obstacles = setOf(GridCoord(1, 2), GridCoord(3, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(0, 4), GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 4)),
                    3 to listOf(GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3))
                )
            ),
            // Level 13 - Pillar Run
            Level(
                id = 13,
                title = "Level 13 - Pillar Run",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(1, 4)),
                    DotPair(2, 2, GridCoord(3, 0), GridCoord(3, 4)),
                    DotPair(3, 4, GridCoord(0, 0), GridCoord(0, 4)),
                    DotPair(4, 5, GridCoord(4, 0), GridCoord(4, 4))
                ),
                obstacles = setOf(GridCoord(2, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4)),
                    2 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4)),
                    4 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4))
                )
            ),
            // Level 14 - Grand Curve
            Level(
                id = 14,
                title = "Level 14 - Grand Curve",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(5, 5)),
                    DotPair(2, 1, GridCoord(0, 1), GridCoord(4, 5)),
                    DotPair(3, 4, GridCoord(2, 2), GridCoord(3, 3))
                ),
                obstacles = setOf(GridCoord(2, 3), GridCoord(3, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    2 to listOf(GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(2, 2), GridCoord(2, 1), GridCoord(3, 1), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(3, 3))
                )
            ),
            // Level 15 - The Corridor
            Level(
                id = 15,
                title = "Level 15 - The Corridor",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 1), GridCoord(4, 1)),
                    DotPair(2, 1, GridCoord(1, 4), GridCoord(4, 4)),
                    DotPair(3, 3, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 4, GridCoord(5, 0), GridCoord(5, 5))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(3, 3)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1), GridCoord(4, 1)),
                    2 to listOf(GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 4)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5))
                )
            ),
            // Level 16 - Master Flow
            Level(
                id = 16,
                title = "Level 16 - Master Flow",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(4, 0)),
                    DotPair(2, 1, GridCoord(1, 5), GridCoord(4, 5)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 3, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3))
                )
            ),
            // Level 17 - Master Flow
            Level(
                id = 17,
                title = "Level 17 - Master Flow",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(4, 0)),
                    DotPair(2, 1, GridCoord(1, 5), GridCoord(4, 5)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 3, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3))
                )
            ),
            // Level 18 - Master Flow
            Level(
                id = 18,
                title = "Level 18 - Master Flow",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(4, 0)),
                    DotPair(2, 1, GridCoord(1, 5), GridCoord(4, 5)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 3, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3))
                )
            ),
            // Level 19 - Master Flow
            Level(
                id = 19,
                title = "Level 19 - Master Flow",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(4, 0)),
                    DotPair(2, 1, GridCoord(1, 5), GridCoord(4, 5)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 3, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3))
                )
            ),
            // Level 20 - Master Flow
            Level(
                id = 20,
                title = "Level 20 - Master Flow",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(4, 0)),
                    DotPair(2, 1, GridCoord(1, 5), GridCoord(4, 5)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 3, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3))
                )
            ),
            // Level 21 - Master Flow
            Level(
                id = 21,
                title = "Level 21 - Master Flow",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(4, 0)),
                    DotPair(2, 1, GridCoord(1, 5), GridCoord(4, 5)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 3, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3))
                )
            ),
            // Level 22 - Master Flow
            Level(
                id = 22,
                title = "Level 22 - Master Flow",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(4, 0)),
                    DotPair(2, 1, GridCoord(1, 5), GridCoord(4, 5)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(4, 3, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3))
                )
            ),
            // Level 23 - Master Flow
            Level(
                id = 23,
                title = "Level 23 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            ),
            // Level 24 - Master Flow
            Level(
                id = 24,
                title = "Level 24 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            ),
            // Level 25 - Master Flow
            Level(
                id = 25,
                title = "Level 25 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            ),
            // Level 26 - Master Flow
            Level(
                id = 26,
                title = "Level 26 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            ),
            // Level 27 - Master Flow
            Level(
                id = 27,
                title = "Level 27 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            ),
            // Level 28 - Master Flow
            Level(
                id = 28,
                title = "Level 28 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            ),
            // Level 29 - Master Flow
            Level(
                id = 29,
                title = "Level 29 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            ),
            // Level 30 - Master Flow
            Level(
                id = 30,
                title = "Level 30 - Master Flow",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(1, 6), GridCoord(5, 6)),
                    DotPair(3, 2, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 3, GridCoord(6, 0), GridCoord(6, 6)),
                    DotPair(5, 4, GridCoord(1, 3), GridCoord(5, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(1, 6), GridCoord(2, 6), GridCoord(3, 6), GridCoord(4, 6), GridCoord(5, 6)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6)),
                    5 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(5, 3))
                )
            )
        )
    }

    fun getLevel(id: Int): Level {
        return levels.find { it.id == id } ?: levels.first()
    }
}