package com.example.twisty.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.twisty.theme.DotBerryPink
import com.example.twisty.theme.DotEmerald
import com.example.twisty.theme.DotSkyCyan
import com.example.twisty.theme.TwistyBgGradientBottom
import com.example.twisty.theme.TwistyBgGradientTop
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Delightful animated background with subtle floating spiral curves and ambient gradients.
 */
@Composable
fun AnimatedBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "BackgroundRibbonLoop")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "BgPhase"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(TwistyBgGradientTop, TwistyBgGradientBottom)
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Floating Twisting Spiral Ribbon 1 (Berry Pink)
            val path1 = Path()
            val startY1 = h * 0.25f
            path1.moveTo(0f, startY1)
            for (x in 0..w.toInt() step 20) {
                val wave = sin(x * 0.008f + phase) * 70f + cos(x * 0.004f + phase * 0.5f) * 40f
                path1.lineTo(x.toFloat(), startY1 + wave)
            }
            drawPath(
                path = path1,
                color = DotBerryPink.copy(alpha = 0.08f),
                style = Stroke(width = 48f, cap = StrokeCap.Round)
            )

            // Floating Twisting Spiral Ribbon 2 (Sky Cyan)
            val path2 = Path()
            val startY2 = h * 0.75f
            path2.moveTo(0f, startY2)
            for (x in 0..w.toInt() step 20) {
                val wave = cos(x * 0.007f + phase * 0.8f) * 80f + sin(x * 0.003f + phase) * 50f
                path2.lineTo(x.toFloat(), startY2 + wave)
            }
            drawPath(
                path = path2,
                color = DotSkyCyan.copy(alpha = 0.07f),
                style = Stroke(width = 56f, cap = StrokeCap.Round)
            )

            // Floating ambient soft glowing orbs
            drawCircle(
                color = DotEmerald.copy(alpha = 0.04f),
                radius = 180f,
                center = Offset(w * 0.85f, h * 0.15f + sin(phase) * 30f)
            )
            drawCircle(
                color = DotBerryPink.copy(alpha = 0.04f),
                radius = 220f,
                center = Offset(w * 0.15f, h * 0.85f + cos(phase) * 40f)
            )
        }

        content()
    }
}
