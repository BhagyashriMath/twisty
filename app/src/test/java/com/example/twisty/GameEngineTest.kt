package com.example.twisty

import com.example.twisty.data.models.DotPair
import com.example.twisty.data.models.GridCoord
import com.example.twisty.data.models.Level
import com.example.twisty.game.logic.GameActionResult
import com.example.twisty.game.logic.TwistyGameEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class GameEngineTest {

    private fun createSampleLevel(): Level {
        return Level(
            id = 1,
            title = "Test Level",
            gridWidth = 4,
            gridHeight = 4,
            pairs = listOf(
                DotPair(pairId = 1, colorId = 0, dotA = GridCoord(0, 0), dotB = GridCoord(0, 3)),
                DotPair(pairId = 2, colorId = 1, dotA = GridCoord(1, 0), dotB = GridCoord(1, 3))
            ),
            obstacles = setOf(GridCoord(3, 3)),
            hintPaths = mapOf(
                1 to listOf(GridCoord(0, 0), GridCoord(0, 1), GridCoord(0, 2), GridCoord(0, 3)),
                2 to listOf(GridCoord(1, 0), GridCoord(1, 1), GridCoord(1, 2), GridCoord(1, 3))
            )
        )
    }

    @Test
    fun testStartTouchOnDot() {
        val level = createSampleLevel()
        val engine = TwistyGameEngine(level)

        val result = engine.onTouchStart(GridCoord(0, 0))
        assertTrue(result is GameActionResult.DotTouched)
        assertEquals(1, engine.state.activePairId)
        assertEquals(1, engine.state.connections[1]?.coords?.size)
    }

    @Test
    fun testExtendPathAndConnect() {
        val level = createSampleLevel()
        val engine = TwistyGameEngine(level)

        engine.onTouchStart(GridCoord(0, 0))
        engine.onTouchMove(GridCoord(0, 1))
        engine.onTouchMove(GridCoord(0, 2))
        val connectResult = engine.onTouchMove(GridCoord(0, 3))

        assertTrue(connectResult is GameActionResult.PairConnected)
        assertTrue(engine.state.connections[1]?.isConnected == true)
        assertFalse(engine.state.isCompleted) // Pair 2 still incomplete
    }

    @Test
    fun testCrossingPrevention() {
        val level = createSampleLevel()
        val engine = TwistyGameEngine(level)

        // Complete pair 1 straight down column 0
        engine.onTouchStart(GridCoord(0, 0))
        engine.onTouchMove(GridCoord(0, 1))
        engine.onTouchMove(GridCoord(0, 2))
        engine.onTouchMove(GridCoord(0, 3))
        engine.onTouchEnd()

        // Try to draw pair 2 into column 0 (crossing!)
        engine.onTouchStart(GridCoord(1, 0))
        val invalidResult = engine.onTouchMove(GridCoord(0, 0)) // other dot!
        assertTrue(invalidResult is GameActionResult.InvalidMove)

        engine.onTouchMove(GridCoord(1, 1))
        val invalidCross = engine.onTouchMove(GridCoord(0, 1)) // occupied by pair 1
        assertTrue(invalidCross is GameActionResult.InvalidMove)
    }

    @Test
    fun testObstacleBlocked() {
        val level = createSampleLevel()
        val engine = TwistyGameEngine(level)

        engine.onTouchStart(GridCoord(1, 0))
        engine.onTouchMove(GridCoord(2, 0))
        engine.onTouchMove(GridCoord(3, 0))
        engine.onTouchMove(GridCoord(3, 1))
        engine.onTouchMove(GridCoord(3, 2))
        val obstacleResult = engine.onTouchMove(GridCoord(3, 3)) // Obstacle!
        assertTrue(obstacleResult is GameActionResult.InvalidMove)
    }

    @Test
    fun testLevelCompletionAndHint() {
        val level = createSampleLevel()
        val engine = TwistyGameEngine(level)

        // Apply hint for pair 1
        val hint1 = engine.applyHint()
        assertNotNull(hint1)
        assertTrue(engine.state.connections[1]?.isConnected == true)
        assertFalse(engine.state.isCompleted)

        // Apply hint for pair 2
        val hint2 = engine.applyHint()
        assertNotNull(hint2)
        assertTrue(engine.state.connections[2]?.isConnected == true)
        assertTrue(engine.state.isCompleted)
        assertEquals(3, engine.state.starsEarned)
    }
}
