package com.example.twisty.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.twisty.theme.StarActive
import com.example.twisty.theme.StarInactive
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun StarRatingView(
    stars: Int,
    modifier: Modifier = Modifier,
    starSize: Dp = 44.dp,
    animated: Boolean = true
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..3) {
            val isEarned = i <= stars
            val scale = remember { Animatable(if (animated && isEarned) 0f else 1f) }

            if (animated && isEarned) {
                LaunchedEffect(i) {
                    delay((i - 1) * 150L)
                    scale.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(350, easing = FastOutSlowInEasing)
                    )
                }
            }

            val currentScale = if (animated && isEarned) scale.value else 1f

            Canvas(modifier = Modifier.size(starSize)) {
                val r = (size.minDimension / 2f) * currentScale
                val center = Offset(size.width / 2f, size.height / 2f)
                val color = if (isEarned) StarActive else StarInactive

                val starPath = createStarPath(center, r, r * 0.45f)
                drawPath(path = starPath, color = color, style = Fill)

                if (isEarned) {
                    // Inner soft shine
                    val innerHighlight = createStarPath(center, r * 0.7f, r * 0.3f)
                    drawPath(path = innerHighlight, color = Color.White.copy(alpha = 0.35f), style = Fill)
                }
            }
        }
    }
}

private fun createStarPath(center: Offset, outerRadius: Float, innerRadius: Float): Path {
    val path = Path()
    val points = 5
    var angle = -PI / 2.0
    val step = PI / points

    for (i in 0 until (points * 2)) {
        val r = if (i % 2 == 0) outerRadius else innerRadius
        val x = (center.x + r * cos(angle)).toFloat()
        val y = (center.y + r * sin(angle)).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        angle += step
    }
    path.close()
    return path
}
