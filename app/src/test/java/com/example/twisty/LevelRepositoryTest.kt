package com.example.twisty

import com.example.twisty.data.levels.LevelRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LevelRepositoryTest {

    @Test
    fun testLevelsCountAndIntegrity() {
        val levels = LevelRepository.levels
        assertEquals("Should have exactly 30 levels", 30, levels.size)

        levels.forEach { level ->
            assertTrue("Level ${level.id} grid width must be >= 4", level.gridWidth >= 4)
            assertTrue("Level ${level.id} grid height must be >= 4", level.gridHeight >= 4)
            assertTrue("Level ${level.id} must have pairs", level.pairs.isNotEmpty())

            val dotCoords = mutableSetOf<Pair<Int, Int>>()

            level.pairs.forEach { pair ->
                assertNotEquals("DotA and DotB must be different for pair ${pair.pairId} in level ${level.id}", pair.dotA, pair.dotB)

                // Bounds check
                assertTrue(
                    "DotA (${pair.dotA.x}, ${pair.dotA.y}) out of bounds in level ${level.id}",
                    pair.dotA.isInside(level.gridWidth, level.gridHeight)
                )
                assertTrue(
                    "DotB (${pair.dotB.x}, ${pair.dotB.y}) out of bounds in level ${level.id}",
                    pair.dotB.isInside(level.gridWidth, level.gridHeight)
                )

                // Overlap check
                val coordA = Pair(pair.dotA.x, pair.dotA.y)
                val coordB = Pair(pair.dotB.x, pair.dotB.y)
                assertTrue("Duplicate dot at $coordA in level ${level.id}", dotCoords.add(coordA))
                assertTrue("Duplicate dot at $coordB in level ${level.id}", dotCoords.add(coordB))
            }

            // Obstacles check
            level.obstacles.forEach { obs ->
                assertTrue(
                    "Obstacle (${obs.x}, ${obs.y}) out of bounds in level ${level.id}",
                    obs.isInside(level.gridWidth, level.gridHeight)
                )
                val obsPair = Pair(obs.x, obs.y)
                assertTrue("Obstacle cannot overlap a dot at $obsPair in level ${level.id}", !dotCoords.contains(obsPair))
            }
        }
    }
}
