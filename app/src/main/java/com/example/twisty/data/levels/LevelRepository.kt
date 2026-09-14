package com.example.twisty.data.levels

import com.example.twisty.data.models.DotPair
import com.example.twisty.data.models.GridCoord
import com.example.twisty.data.models.Level

object LevelRepository {

    /**
     * Color indices:
     * 0: Berry Pink
     * 1: Tangerine
     * 2: Lemon Glow
     * 3: Emerald Mint
     * 4: Sky Cyan
     * 5: Royal Purple
     */

    val levels: List<Level> by lazy {
        listOf(
            // Level 1: 4x4 - 2 pairs - Simple intro
            Level(
                id = 1,
                title = "Level 1 - Sweet Start",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 3)), // Berry (col 0)
                    DotPair(2, 4, GridCoord(3, 0), GridCoord(3, 3))  // Cyan (col 3)
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3)),
                    2 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3))
                )
            ),

            // Level 2: 4x4 - 2 pairs - Gentle curve
            Level(
                id = 2,
                title = "Level 2 - First Bend",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 1, GridCoord(0, 0), GridCoord(3, 0)), // Tangerine (top row)
                    DotPair(2, 3, GridCoord(0, 3), GridCoord(3, 3))  // Emerald (bottom row)
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0)),
                    2 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3))
                )
            ),

            // Level 3: 4x4 - 3 pairs - Parallel routing
            Level(
                id = 3,
                title = "Level 3 - Triple Flow",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 3)), // Berry
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 3)), // Tangerine
                    DotPair(3, 4, GridCoord(2, 0), GridCoord(2, 3))  // Cyan
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3))
                )
            ),

            // Level 4: 4x4 - 3 pairs - Wrapping curve
            Level(
                id = 4,
                title = "Level 4 - Twisty Loop",
                gridWidth = 4,
                gridHeight = 4,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(3, 1)), // Berry: wraps top then right
                    DotPair(2, 2, GridCoord(1, 1), GridCoord(2, 1)), // Lemon: short center
                    DotPair(3, 3, GridCoord(0, 3), GridCoord(3, 3))  // Emerald: bottom row
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(3, 1)),
                    2 to listOf(GridCoord(1, 1), GridCoord(2, 1)),
                    3 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3))
                )
            ),

            // Level 5: 5x5 - 3 pairs - Corner wrap
            Level(
                id = 5,
                title = "Level 5 - Candy Wrap",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(4, 4)), // Berry: perimeter
                    DotPair(2, 4, GridCoord(1, 1), GridCoord(3, 3)), // Cyan: inner diagonal
                    DotPair(3, 1, GridCoord(0, 4), GridCoord(4, 0))  // Tangerine
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3), GridCoord(4, 4)),
                    2 to listOf(GridCoord(1, 1), GridCoord(1, 2), GridCoord(2, 2), GridCoord(3, 2), GridCoord(3, 3)),
                    3 to listOf(GridCoord(0, 4), GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 4))
                )
            ),

            // Level 6: 5x5 - 4 pairs - More colors
            Level(
                id = 6,
                title = "Level 6 - Rainbow Weave",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 4)), // Berry
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 4)), // Tangerine
                    DotPair(3, 2, GridCoord(2, 0), GridCoord(2, 4)), // Lemon
                    DotPair(4, 3, GridCoord(3, 0), GridCoord(3, 4))  // Emerald
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3), GridCoord(2, 4)),
                    4 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4))
                )
            ),

            // Level 7: 5x5 - 4 pairs - Spiral cross paths
            Level(
                id = 7,
                title = "Level 7 - Spiral Twist",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(4, 0)), // Berry (top)
                    DotPair(2, 4, GridCoord(0, 1), GridCoord(0, 4)), // Cyan (left)
                    DotPair(3, 1, GridCoord(1, 1), GridCoord(4, 4)), // Tangerine
                    DotPair(4, 3, GridCoord(1, 4), GridCoord(3, 4))  // Emerald (bottom)
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0)),
                    2 to listOf(GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4)),
                    3 to listOf(GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4)),
                    4 to listOf(GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4))
                )
            ),

            // Level 8: 5x5 - 4 pairs - Jalebi curves
            Level(
                id = 8,
                title = "Level 8 - Jalebi Swirl",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 2), GridCoord(4, 2)), // Berry
                    DotPair(2, 2, GridCoord(2, 0), GridCoord(2, 4)), // Lemon
                    DotPair(3, 4, GridCoord(0, 0), GridCoord(4, 4)), // Cyan
                    DotPair(4, 5, GridCoord(0, 4), GridCoord(4, 0))  // Purple
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 2), GridCoord(1, 2), GridCoord(2, 2), GridCoord(3, 2), GridCoord(4, 2)),
                    2 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(1, 1), GridCoord(1, 3), GridCoord(2, 3), GridCoord(2, 4)),
                    3 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(0, 1), GridCoord(0, 3), GridCoord(0, 4), GridCoord(1, 4), GridCoord(4, 4)),
                    4 to listOf(GridCoord(0, 4), GridCoord(0, 3), GridCoord(4, 1), GridCoord(4, 0))
                )
            ),

            // Level 9: 5x5 - 4 pairs - Tight labyrinth
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

            // Level 10: 5x5 - 5 pairs - Master 5x5
            Level(
                id = 10,
                title = "Level 10 - Five Twists",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 3)),
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 3)),
                    DotPair(3, 2, GridCoord(2, 0), GridCoord(2, 3)),
                    DotPair(4, 3, GridCoord(3, 0), GridCoord(3, 3)),
                    DotPair(5, 4, GridCoord(4, 0), GridCoord(4, 3))
                ),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3)),
                    4 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3)),
                    5 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3))
                )
            ),

            // Level 11: 5x5 - 3 pairs - First Obstacle!
            Level(
                id = 11,
                title = "Level 11 - The Blockade",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 1), GridCoord(3, 1)),
                    DotPair(2, 4, GridCoord(1, 3), GridCoord(3, 3)),
                    DotPair(3, 1, GridCoord(0, 2), GridCoord(4, 2))
                ),
                obstacles = setOf(GridCoord(2, 2)), // Center blocked!
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1)),
                    2 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3)),
                    3 to listOf(GridCoord(0, 2), GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(4, 2))
                )
            ),

            // Level 12: 5x5 - 3 pairs - Dual Obstacles
            Level(
                id = 12,
                title = "Level 12 - Twin Rocks",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(4, 0)),
                    DotPair(2, 3, GridCoord(0, 4), GridCoord(4, 4)),
                    DotPair(3, 1, GridCoord(2, 0), GridCoord(2, 4))
                ),
                obstacles = setOf(GridCoord(1, 2), GridCoord(3, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0)), // waits, direct 0,0->4,0
                    2 to listOf(GridCoord(0, 4), GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 4)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3), GridCoord(2, 4))
                )
            ),

            // Level 13: 5x5 - 4 pairs - Pillar Obstacle
            Level(
                id = 13,
                title = "Level 13 - Pillar Run",
                gridWidth = 5,
                gridHeight = 5,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 1), GridCoord(4, 1)),
                    DotPair(2, 2, GridCoord(0, 3), GridCoord(4, 3)),
                    DotPair(3, 4, GridCoord(1, 0), GridCoord(1, 4)),
                    DotPair(4, 5, GridCoord(3, 0), GridCoord(3, 4))
                ),
                obstacles = setOf(GridCoord(2, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 1), GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1), GridCoord(4, 1)),
                    2 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3)),
                    3 to listOf(GridCoord(1, 0), GridCoord(0, 0), GridCoord(0, 2), GridCoord(0, 4), GridCoord(1, 4)),
                    4 to listOf(GridCoord(3, 0), GridCoord(4, 0), GridCoord(4, 2), GridCoord(4, 4), GridCoord(3, 4))
                )
            ),

            // Level 14: 6x6 - 3 pairs - Expanding space
            Level(
                id = 14,
                title = "Level 14 - Grand Curve",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(5, 5)),
                    DotPair(2, 1, GridCoord(0, 5), GridCoord(5, 0)),
                    DotPair(3, 4, GridCoord(2, 2), GridCoord(3, 3))
                ),
                obstacles = setOf(GridCoord(2, 3), GridCoord(3, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    2 to listOf(GridCoord(0, 5), GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5), GridCoord(4, 4), GridCoord(4, 3), GridCoord(4, 2), GridCoord(4, 1), GridCoord(5, 0)),
                    3 to listOf(GridCoord(2, 2), GridCoord(2, 1), GridCoord(3, 1), GridCoord(3, 3))
                )
            ),

            // Level 15: 6x6 - 4 pairs - Corridor
            Level(
                id = 15,
                title = "Level 15 - The Corridor",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 1), GridCoord(4, 1)),
                    DotPair(2, 1, GridCoord(1, 4), GridCoord(4, 4)),
                    DotPair(3, 3, GridCoord(0, 2), GridCoord(5, 2)),
                    DotPair(4, 4, GridCoord(0, 3), GridCoord(5, 3))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(3, 3)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1), GridCoord(4, 1)),
                    2 to listOf(GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 4)),
                    3 to listOf(GridCoord(0, 2), GridCoord(1, 2), GridCoord(1, 3), GridCoord(2, 3), GridCoord(4, 3), GridCoord(5, 2)),
                    4 to listOf(GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(5, 5), GridCoord(5, 3))
                )
            ),

            // Level 16: 6x6 - 4 pairs - Crossroad Obstacles
            Level(
                id = 16,
                title = "Level 16 - Crossroad",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 5)),
                    DotPair(2, 2, GridCoord(5, 0), GridCoord(5, 5)),
                    DotPair(3, 3, GridCoord(1, 2), GridCoord(4, 2)),
                    DotPair(4, 5, GridCoord(1, 3), GridCoord(4, 3))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(3, 3)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5)),
                    2 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5)),
                    3 to listOf(GridCoord(1, 2), GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1), GridCoord(4, 1), GridCoord(4, 2)),
                    4 to listOf(GridCoord(1, 3), GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 4), GridCoord(4, 3))
                )
            ),

            // Level 17: 6x6 - 4 pairs - Island in the center
            Level(
                id = 17,
                title = "Level 17 - Candy Island",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 1), GridCoord(5, 1)),
                    DotPair(2, 1, GridCoord(0, 4), GridCoord(5, 4)),
                    DotPair(3, 2, GridCoord(1, 0), GridCoord(1, 5)),
                    DotPair(4, 4, GridCoord(4, 0), GridCoord(4, 5))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(3, 2), GridCoord(2, 3), GridCoord(3, 3)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 1), GridCoord(0, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(5, 0), GridCoord(5, 1)),
                    2 to listOf(GridCoord(0, 4), GridCoord(0, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(5, 5), GridCoord(5, 4)),
                    3 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4), GridCoord(1, 5)),
                    4 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4), GridCoord(4, 5))
                )
            ),

            // Level 18: 6x6 - 4 pairs - Diagonal Barrier
            Level(
                id = 18,
                title = "Level 18 - Zigzag",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 2), GridCoord(5, 2)),
                    DotPair(2, 3, GridCoord(0, 4), GridCoord(5, 4)),
                    DotPair(3, 4, GridCoord(2, 0), GridCoord(2, 5)),
                    DotPair(4, 5, GridCoord(4, 0), GridCoord(4, 5))
                ),
                obstacles = setOf(GridCoord(1, 1), GridCoord(3, 3)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 2), GridCoord(1, 2), GridCoord(2, 2), GridCoord(3, 2), GridCoord(4, 2), GridCoord(5, 2)),
                    2 to listOf(GridCoord(0, 4), GridCoord(1, 4), GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 4), GridCoord(5, 4)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(1, 3), GridCoord(2, 5)),
                    4 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 3), GridCoord(4, 5))
                )
            ),

            // Level 19: 6x6 - 5 pairs - Rich Spectrum
            Level(
                id = 19,
                title = "Level 19 - Neon Ribbon",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 4)),
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 4)),
                    DotPair(3, 2, GridCoord(2, 0), GridCoord(2, 4)),
                    DotPair(4, 3, GridCoord(3, 0), GridCoord(3, 4)),
                    DotPair(5, 4, GridCoord(4, 0), GridCoord(4, 4))
                ),
                obstacles = setOf(GridCoord(5, 2)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3), GridCoord(2, 4)),
                    4 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4)),
                    5 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4))
                )
            ),

            // Level 20: 6x6 - 5 pairs - Labyrinth Climax
            Level(
                id = 20,
                title = "Level 20 - Twisty Champion",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(5, 0)),
                    DotPair(2, 1, GridCoord(0, 5), GridCoord(5, 5)),
                    DotPair(3, 2, GridCoord(1, 2), GridCoord(4, 2)),
                    DotPair(4, 3, GridCoord(1, 3), GridCoord(4, 3)),
                    DotPair(5, 5, GridCoord(0, 2), GridCoord(0, 3))
                ),
                obstacles = setOf(GridCoord(2, 1), GridCoord(3, 4)),
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(3, 0), GridCoord(4, 0), GridCoord(5, 0)),
                    2 to listOf(GridCoord(0, 5), GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5), GridCoord(5, 5)),
                    3 to listOf(GridCoord(1, 2), GridCoord(2, 2), GridCoord(3, 2), GridCoord(4, 2)),
                    4 to listOf(GridCoord(1, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(4, 3)),
                    5 to listOf(GridCoord(0, 2), GridCoord(0, 3))
                )
            ),

            // Level 21: 6x6 - 4 pairs - Move Challenge (12 moves)
            Level(
                id = 21,
                title = "Level 21 - Precision Step",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 0), GridCoord(1, 5)),
                    DotPair(2, 1, GridCoord(4, 0), GridCoord(4, 5)),
                    DotPair(3, 3, GridCoord(0, 2), GridCoord(5, 2)),
                    DotPair(4, 4, GridCoord(0, 4), GridCoord(5, 4))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(3, 4)),
                moveLimit = 8,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4), GridCoord(1, 5)),
                    2 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4), GridCoord(4, 5)),
                    3 to listOf(GridCoord(0, 2), GridCoord(0, 1), GridCoord(2, 1), GridCoord(3, 1), GridCoord(5, 1), GridCoord(5, 2)),
                    4 to listOf(GridCoord(0, 4), GridCoord(0, 3), GridCoord(2, 3), GridCoord(3, 3), GridCoord(5, 3), GridCoord(5, 4))
                )
            ),

            // Level 22: 6x6 - 5 pairs - Tight Choke
            Level(
                id = 22,
                title = "Level 22 - The Choke",
                gridWidth = 6,
                gridHeight = 6,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 1), GridCoord(5, 1)),
                    DotPair(2, 2, GridCoord(0, 3), GridCoord(5, 3)),
                    DotPair(3, 3, GridCoord(1, 0), GridCoord(1, 5)),
                    DotPair(4, 4, GridCoord(3, 0), GridCoord(3, 5)),
                    DotPair(5, 5, GridCoord(5, 0), GridCoord(5, 5))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(4, 4)),
                moveLimit = 10,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 1), GridCoord(2, 1), GridCoord(4, 1), GridCoord(5, 1)),
                    2 to listOf(GridCoord(0, 3), GridCoord(2, 3), GridCoord(4, 3), GridCoord(5, 3)),
                    3 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4), GridCoord(1, 5)),
                    4 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4), GridCoord(3, 5)),
                    5 to listOf(GridCoord(5, 0), GridCoord(5, 2), GridCoord(5, 4), GridCoord(5, 5))
                )
            ),

            // Level 23: 7x7 - 4 pairs - Giant Grid
            Level(
                id = 23,
                title = "Level 23 - Mega Canvas",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(6, 0)),
                    DotPair(2, 1, GridCoord(0, 6), GridCoord(6, 6)),
                    DotPair(3, 4, GridCoord(0, 3), GridCoord(6, 3)),
                    DotPair(4, 3, GridCoord(3, 0), GridCoord(3, 6))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(4, 4), GridCoord(2, 4), GridCoord(4, 2)),
                moveLimit = 8,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(4, 0), GridCoord(5, 0), GridCoord(6, 0)),
                    2 to listOf(GridCoord(0, 6), GridCoord(1, 6), GridCoord(2, 6), GridCoord(4, 6), GridCoord(5, 6), GridCoord(6, 6)),
                    3 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(2, 3), GridCoord(4, 3), GridCoord(5, 3), GridCoord(6, 3)),
                    4 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4), GridCoord(3, 5), GridCoord(3, 6))
                )
            ),

            // Level 24: 7x7 - 4 pairs - Fortress
            Level(
                id = 24,
                title = "Level 24 - Candy Fortress",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(1, 1), GridCoord(5, 1)),
                    DotPair(2, 2, GridCoord(1, 5), GridCoord(5, 5)),
                    DotPair(3, 3, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(4, 5, GridCoord(6, 0), GridCoord(6, 6))
                ),
                obstacles = setOf(GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4)),
                moveLimit = 8,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(1, 1), GridCoord(2, 1), GridCoord(3, 1), GridCoord(4, 1), GridCoord(5, 1)),
                    2 to listOf(GridCoord(1, 5), GridCoord(2, 5), GridCoord(3, 5), GridCoord(4, 5), GridCoord(5, 5)),
                    3 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    4 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6))
                )
            ),

            // Level 25: 7x7 - 5 pairs - Diamond Obstacles
            Level(
                id = 25,
                title = "Level 25 - The Diamond",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 1), GridCoord(6, 1)),
                    DotPair(2, 1, GridCoord(0, 5), GridCoord(6, 5)),
                    DotPair(3, 2, GridCoord(1, 0), GridCoord(1, 6)),
                    DotPair(4, 4, GridCoord(5, 0), GridCoord(5, 6)),
                    DotPair(5, 3, GridCoord(3, 1), GridCoord(3, 5))
                ),
                obstacles = setOf(GridCoord(3, 3), GridCoord(2, 3), GridCoord(4, 3)),
                moveLimit = 10,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 1), GridCoord(2, 1), GridCoord(4, 1), GridCoord(6, 1)),
                    2 to listOf(GridCoord(0, 5), GridCoord(2, 5), GridCoord(4, 5), GridCoord(6, 5)),
                    3 to listOf(GridCoord(1, 0), GridCoord(1, 2), GridCoord(1, 4), GridCoord(1, 6)),
                    4 to listOf(GridCoord(5, 0), GridCoord(5, 2), GridCoord(5, 4), GridCoord(5, 6)),
                    5 to listOf(GridCoord(3, 1), GridCoord(3, 2), GridCoord(2, 2), GridCoord(2, 4), GridCoord(3, 4), GridCoord(3, 5))
                )
            ),

            // Level 26: 7x7 - 5 pairs - Quad Gates
            Level(
                id = 26,
                title = "Level 26 - Quad Gates",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(6, 6)),
                    DotPair(2, 1, GridCoord(0, 6), GridCoord(6, 0)),
                    DotPair(3, 2, GridCoord(2, 2), GridCoord(4, 4)),
                    DotPair(4, 4, GridCoord(2, 4), GridCoord(4, 2)),
                    DotPair(5, 5, GridCoord(3, 0), GridCoord(3, 6))
                ),
                obstacles = setOf(GridCoord(1, 3), GridCoord(5, 3)),
                moveLimit = 10,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(4, 0), GridCoord(5, 0), GridCoord(6, 0), GridCoord(6, 6)),
                    2 to listOf(GridCoord(0, 6), GridCoord(1, 6), GridCoord(2, 6), GridCoord(4, 6), GridCoord(5, 6), GridCoord(6, 6)),
                    3 to listOf(GridCoord(2, 2), GridCoord(3, 2), GridCoord(4, 4)),
                    4 to listOf(GridCoord(2, 4), GridCoord(3, 4), GridCoord(4, 2)),
                    5 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 3), GridCoord(3, 5), GridCoord(3, 6))
                )
            ),

            // Level 27: 7x7 - 5 pairs - Serpent
            Level(
                id = 27,
                title = "Level 27 - Great Serpent",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 2), GridCoord(6, 2)),
                    DotPair(2, 1, GridCoord(0, 4), GridCoord(6, 4)),
                    DotPair(3, 3, GridCoord(2, 0), GridCoord(2, 6)),
                    DotPair(4, 4, GridCoord(4, 0), GridCoord(4, 6)),
                    DotPair(5, 2, GridCoord(0, 0), GridCoord(6, 6))
                ),
                obstacles = setOf(GridCoord(1, 1), GridCoord(5, 5), GridCoord(1, 5), GridCoord(5, 1)),
                moveLimit = 11,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 2), GridCoord(1, 2), GridCoord(3, 2), GridCoord(5, 2), GridCoord(6, 2)),
                    2 to listOf(GridCoord(0, 4), GridCoord(1, 4), GridCoord(3, 4), GridCoord(5, 4), GridCoord(6, 4)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 3), GridCoord(2, 5), GridCoord(2, 6)),
                    4 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 3), GridCoord(4, 5), GridCoord(4, 6)),
                    5 to listOf(GridCoord(0, 0), GridCoord(0, 6), GridCoord(6, 6))
                )
            ),

            // Level 28: 7x7 - 5 pairs - Crystal Labyrinth
            Level(
                id = 28,
                title = "Level 28 - Crystal Maze",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 3), GridCoord(6, 3)),
                    DotPair(2, 1, GridCoord(3, 0), GridCoord(3, 6)),
                    DotPair(3, 2, GridCoord(1, 1), GridCoord(5, 5)),
                    DotPair(4, 4, GridCoord(1, 5), GridCoord(5, 1)),
                    DotPair(5, 5, GridCoord(0, 0), GridCoord(6, 0))
                ),
                obstacles = setOf(GridCoord(2, 2), GridCoord(4, 4), GridCoord(2, 4), GridCoord(4, 2), GridCoord(3, 3)),
                moveLimit = 11,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(5, 3), GridCoord(6, 3)),
                    2 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 5), GridCoord(3, 6)),
                    3 to listOf(GridCoord(1, 1), GridCoord(1, 2), GridCoord(5, 4), GridCoord(5, 5)),
                    4 to listOf(GridCoord(1, 5), GridCoord(1, 4), GridCoord(5, 2), GridCoord(5, 1)),
                    5 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(4, 0), GridCoord(5, 0), GridCoord(6, 0))
                )
            ),

            // Level 29: 7x7 - 6 pairs - Spectrum Vortex
            Level(
                id = 29,
                title = "Level 29 - Spectrum Vortex",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(0, 6)),
                    DotPair(2, 1, GridCoord(1, 0), GridCoord(1, 6)),
                    DotPair(3, 2, GridCoord(2, 0), GridCoord(2, 6)),
                    DotPair(4, 3, GridCoord(4, 0), GridCoord(4, 6)),
                    DotPair(5, 4, GridCoord(5, 0), GridCoord(5, 6)),
                    DotPair(6, 5, GridCoord(6, 0), GridCoord(6, 6))
                ),
                obstacles = setOf(GridCoord(3, 2), GridCoord(3, 3), GridCoord(3, 4)),
                moveLimit = 12,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3), GridCoord(0, 4), GridCoord(0, 5), GridCoord(0, 6)),
                    2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3), GridCoord(1, 4), GridCoord(1, 5), GridCoord(1, 6)),
                    3 to listOf(GridCoord(2, 0), GridCoord(2, 1), GridCoord(2, 2), GridCoord(2, 3), GridCoord(2, 4), GridCoord(2, 5), GridCoord(2, 6)),
                    4 to listOf(GridCoord(4, 0), GridCoord(4, 1), GridCoord(4, 2), GridCoord(4, 3), GridCoord(4, 4), GridCoord(4, 5), GridCoord(4, 6)),
                    5 to listOf(GridCoord(5, 0), GridCoord(5, 1), GridCoord(5, 2), GridCoord(5, 3), GridCoord(5, 4), GridCoord(5, 5), GridCoord(5, 6)),
                    6 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 3), GridCoord(6, 4), GridCoord(6, 5), GridCoord(6, 6))
                )
            ),

            // Level 30: 7x7 - 6 pairs - The Grand Master
            Level(
                id = 30,
                title = "Level 30 - Grand Master",
                gridWidth = 7,
                gridHeight = 7,
                pairs = listOf(
                    DotPair(1, 0, GridCoord(0, 0), GridCoord(6, 6)),
                    DotPair(2, 1, GridCoord(6, 0), GridCoord(0, 6)),
                    DotPair(3, 2, GridCoord(1, 2), GridCoord(5, 4)),
                    DotPair(4, 3, GridCoord(5, 2), GridCoord(1, 4)),
                    DotPair(5, 4, GridCoord(3, 0), GridCoord(3, 6)),
                    DotPair(6, 5, GridCoord(0, 3), GridCoord(6, 3))
                ),
                obstacles = setOf(GridCoord(2, 3), GridCoord(4, 3), GridCoord(3, 2), GridCoord(3, 4)),
                moveLimit = 12,
                hintPaths = mapOf(
                    1 to listOf(GridCoord(0, 0), GridCoord(1, 0), GridCoord(2, 0), GridCoord(4, 0), GridCoord(5, 0), GridCoord(6, 0), GridCoord(6, 6)),
                    2 to listOf(GridCoord(6, 0), GridCoord(6, 1), GridCoord(6, 2), GridCoord(6, 4), GridCoord(6, 5), GridCoord(0, 6)),
                    3 to listOf(GridCoord(1, 2), GridCoord(2, 2), GridCoord(5, 4)),
                    4 to listOf(GridCoord(5, 2), GridCoord(4, 2), GridCoord(1, 4)),
                    5 to listOf(GridCoord(3, 0), GridCoord(3, 1), GridCoord(3, 5), GridCoord(3, 6)),
                    6 to listOf(GridCoord(0, 3), GridCoord(1, 3), GridCoord(5, 3), GridCoord(6, 3))
                )
            )
        )
    }

    fun getLevel(id: Int): Level {
        return levels.find { it.id == id } ?: levels.first()
    }
}
