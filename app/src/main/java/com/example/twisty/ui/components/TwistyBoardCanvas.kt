package com.example.twisty.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.twisty.data.models.GameState
import com.example.twisty.data.models.GridCoord
import com.example.twisty.theme.DotBerryPink
import com.example.twisty.theme.DotCoral
import com.example.twisty.theme.DotEmerald
import com.example.twisty.theme.DotLemon
import com.example.twisty.theme.DotLime
import com.example.twisty.theme.DotRoyalPurple
import com.example.twisty.theme.DotSkyCyan
import com.example.twisty.theme.DotTangerine
import com.example.twisty.theme.TwistyCardBorder
import com.example.twisty.theme.TwistyGridBg
import com.example.twisty.theme.TwistyGridCell
import com.example.twisty.theme.TwistyObstacle
import com.example.twisty.theme.TwistyObstaclePattern
import kotlin.math.min

val PaletteColors = listOf(
    DotBerryPink,
    DotTangerine,
    DotLemon,
    DotEmerald,
    DotSkyCyan,
    DotRoyalPurple,
    DotCoral,
    DotLime
)

fun getDotColor(colorId: Int): Color {
    return PaletteColors.getOrElse(colorId) { DotBerryPink }
}

@Composable
fun TwistyBoardCanvas(
    gameState: GameState,
    onCellTouchStart: (GridCoord) -> Unit,
    onCellTouchMove: (GridCoord) -> Unit,
    onCellTouchEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ActivePulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(650, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseScale"
    )

    var currentDragScreenOffset by remember { mutableStateOf<Offset?>(null) }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .aspectRatio(1f)
    ) {
        val level = gameState.level

        Canvas(
            modifier = Modifier
                .matchParentSize()
                .pointerInput(level.id) {
                    detectTapGestures(
                        onTap = { offset ->
                            val boardSize = min(size.width, size.height) * 0.94f
                            val cellSize = boardSize / maxOf(level.gridWidth, level.gridHeight)
                            val boardLeft = (size.width - level.gridWidth * cellSize) / 2f
                            val boardTop = (size.height - level.gridHeight * cellSize) / 2f

                            val col = ((offset.x - boardLeft) / cellSize).toInt()
                            val row = ((offset.y - boardTop) / cellSize).toInt()
                            val coord = GridCoord(col, row)
                            if (coord.isInside(level.gridWidth, level.gridHeight)) {
                                onCellTouchStart(coord)
                                onCellTouchEnd()
                            }
                        }
                    )
                }
                .pointerInput(level.id) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            currentDragScreenOffset = offset
                            val boardSize = min(size.width, size.height) * 0.94f
                            val cellSize = boardSize / maxOf(level.gridWidth, level.gridHeight)
                            val boardLeft = (size.width - level.gridWidth * cellSize) / 2f
                            val boardTop = (size.height - level.gridHeight * cellSize) / 2f

                            val col = ((offset.x - boardLeft) / cellSize).toInt()
                            val row = ((offset.y - boardTop) / cellSize).toInt()
                            val coord = GridCoord(col, row)
                            if (coord.isInside(level.gridWidth, level.gridHeight)) {
                                onCellTouchStart(coord)
                            }
                        },
                        onDrag = { change, _ ->
                            change.consume()
                            val offset = change.position
                            currentDragScreenOffset = offset
                            val boardSize = min(size.width, size.height) * 0.94f
                            val cellSize = boardSize / maxOf(level.gridWidth, level.gridHeight)
                            val boardLeft = (size.width - level.gridWidth * cellSize) / 2f
                            val boardTop = (size.height - level.gridHeight * cellSize) / 2f

                            val col = ((offset.x - boardLeft) / cellSize).toInt()
                            val row = ((offset.y - boardTop) / cellSize).toInt()
                            val coord = GridCoord(col, row)
                            if (coord.isInside(level.gridWidth, level.gridHeight)) {
                                onCellTouchMove(coord)
                            }
                        },
                        onDragEnd = {
                            currentDragScreenOffset = null
                            onCellTouchEnd()
                        },
                        onDragCancel = {
                            currentDragScreenOffset = null
                            onCellTouchEnd()
                        }
                    )
                }
        ) {
            val boardDimension = min(size.width, size.height) * 0.94f
            val maxGridAxis = maxOf(level.gridWidth, level.gridHeight)
            val cellSize = boardDimension / maxGridAxis
            val totalBoardWidth = level.gridWidth * cellSize
            val totalBoardHeight = level.gridHeight * cellSize
            val boardLeft = (size.width - totalBoardWidth) / 2f
            val boardTop = (size.height - totalBoardHeight) / 2f

            // 1. Board Background Shadow & Plate
            drawRoundRect(
                color = TwistyGridBg,
                topLeft = Offset(boardLeft, boardTop),
                size = Size(totalBoardWidth, totalBoardHeight),
                cornerRadius = CornerRadius(24f, 24f),
                style = Fill
            )
            drawRoundRect(
                color = TwistyCardBorder,
                topLeft = Offset(boardLeft, boardTop),
                size = Size(totalBoardWidth, totalBoardHeight),
                cornerRadius = CornerRadius(24f, 24f),
                style = Stroke(width = 4f)
            )

            // 2. Grid Cells
            val cellRadius = 14f
            for (col in 0 until level.gridWidth) {
                for (row in 0 until level.gridHeight) {
                    val coord = GridCoord(col, row)
                    val cellLeft = boardLeft + col * cellSize + 3f
                    val cellTop = boardTop + row * cellSize + 3f
                    val cellInnerSize = cellSize - 6f

                    if (level.obstacles.contains(coord)) {
                        // Obstacle Cell: Textured striped block
                        drawObstacleCell(cellLeft, cellTop, cellInnerSize, cellRadius)
                    } else {
                        // Empty playable cell
                        drawRoundRect(
                            color = TwistyGridCell,
                            topLeft = Offset(cellLeft, cellTop),
                            size = Size(cellInnerSize, cellInnerSize),
                            cornerRadius = CornerRadius(cellRadius, cellRadius),
                            style = Fill
                        )
                    }
                }
            }

            // 3. Render Drawn Paths (Twisting curves)
            gameState.connections.values.forEach { connection ->
                if (connection.coords.size > 1) {
                    val color = getDotColor(connection.colorId)
                    val path = Path()

                    val firstPt = getCellCenter(boardLeft, boardTop, cellSize, connection.coords.first())
                    path.moveTo(firstPt.x, firstPt.y)

                    for (i in 1 until connection.coords.size) {
                        val pt = getCellCenter(boardLeft, boardTop, cellSize, connection.coords[i])
                        path.lineTo(pt.x, pt.y)
                    }

                    // A. Outer soft ambient glow
                    drawPath(
                        path = path,
                        color = color.copy(alpha = 0.28f),
                        style = Stroke(
                            width = cellSize * 0.56f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )

                    // B. Main thick juicy twisting tube
                    drawPath(
                        path = path,
                        color = color,
                        style = Stroke(
                            width = cellSize * 0.38f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )

                    // C. Core glossy highlight line
                    drawPath(
                        path = path,
                        color = Color.White.copy(alpha = 0.40f),
                        style = Stroke(
                            width = cellSize * 0.12f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                }
            }

            // 4. Render Dots / Endpoints
            level.pairs.forEach { pair ->
                val color = getDotColor(pair.colorId)
                val isActive = gameState.activePairId == pair.pairId
                val isConn = gameState.connections[pair.pairId]?.isConnected == true

                listOf(pair.dotA, pair.dotB).forEach { dotCoord ->
                    val center = getCellCenter(boardLeft, boardTop, cellSize, dotCoord)
                    val baseRadius = cellSize * 0.28f

                    // Pulsing ring if active
                    if (isActive) {
                        drawCircle(
                            color = color.copy(alpha = 0.35f),
                            radius = baseRadius * pulseScale * 1.3f,
                            center = center
                        )
                    }

                    // Outer dark border ring
                    drawCircle(
                        color = TwistyGridBg,
                        radius = baseRadius + 3f,
                        center = center
                    )

                    // Main vibrant dot
                    drawCircle(
                        color = color,
                        radius = baseRadius,
                        center = center
                    )

                    // Connected checkmark or center jewel
                    if (isConn) {
                        drawCircle(
                            color = Color.White,
                            radius = baseRadius * 0.42f,
                            center = center
                        )
                        drawCircle(
                            color = color,
                            radius = baseRadius * 0.24f,
                            center = center
                        )
                    } else {
                        // Glossy shine highlight
                        drawCircle(
                            color = Color.White.copy(alpha = 0.55f),
                            radius = baseRadius * 0.35f,
                            center = Offset(center.x - baseRadius * 0.3f, center.y - baseRadius * 0.3f)
                        )
                    }
                }
            }

            // 5. Active Drag Cursor Indicator
            if (gameState.activePairId != null) {
                val activePair = level.pairs.firstOrNull { it.pairId == gameState.activePairId }
                if (activePair != null) {
                    val activeColor = getDotColor(activePair.colorId)
                    val cursorCoord = gameState.currentDragCoord ?: gameState.connections[activePair.pairId]?.coords?.lastOrNull()
                    if (cursorCoord != null) {
                        val cursorCenter = getCellCenter(boardLeft, boardTop, cellSize, cursorCoord)
                        drawCircle(
                            color = activeColor.copy(alpha = 0.25f),
                            radius = cellSize * 0.45f,
                            center = cursorCenter
                        )
                    }
                }
            }
        }
    }
}

private fun DrawScope.drawObstacleCell(
    left: Float,
    top: Float,
    size: Float,
    radius: Float
) {
    val rect = RoundRect(
        left = left,
        top = top,
        right = left + size,
        bottom = top + size,
        cornerRadius = CornerRadius(radius, radius)
    )
    val clipPath = Path().apply { addRoundRect(rect) }

    // Solid obstacle background
    drawRoundRect(
        color = TwistyObstacle,
        topLeft = Offset(left, top),
        size = Size(size, size),
        cornerRadius = CornerRadius(radius, radius)
    )

    // Candy diagonal stripes inside the obstacle
    clipPath(clipPath) {
        val step = 14f
        var x = -size
        while (x < size * 2f) {
            drawLine(
                color = TwistyObstaclePattern,
                start = Offset(left + x, top),
                end = Offset(left + x + size, top + size),
                strokeWidth = 5f
            )
            x += step
        }
    }

    // Outer subtle border
    drawRoundRect(
        color = TwistyObstaclePattern.copy(alpha = 0.6f),
        topLeft = Offset(left, top),
        size = Size(size, size),
        cornerRadius = CornerRadius(radius, radius),
        style = Stroke(width = 2f)
    )
}

private fun getCellCenter(boardLeft: Float, boardTop: Float, cellSize: Float, coord: GridCoord): Offset {
    return Offset(
        boardLeft + (coord.x + 0.5f) * cellSize,
        boardTop + (coord.y + 0.5f) * cellSize
    )
}
