package com.example.twisty.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import com.example.twisty.theme.DotBerryPink
import com.example.twisty.theme.DotEmerald
import com.example.twisty.theme.DotLemon
import com.example.twisty.theme.DotRoyalPurple
import com.example.twisty.theme.DotSkyCyan
import com.example.twisty.theme.DotTangerine
import kotlin.random.Random

private data class Particle(
    val initialX: Float,
    val initialY: Float,
    val speedX: Float,
    val speedY: Float,
    val color: Color,
    val size: Float,
    val rotationSpeed: Float
)

@Composable
fun ConfettiView(
    modifier: Modifier = Modifier,
    particleCount: Int = 60
) {
    var trigger by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (trigger) 1f else 0f,
        animationSpec = tween(1500, easing = LinearEasing),
        label = "ConfettiProgress"
    )

    val confettiColors = listOf(
        DotBerryPink, DotTangerine, DotLemon, DotEmerald, DotSkyCyan, DotRoyalPurple
    )

    val particles = remember {
        List(particleCount) {
            val random = Random(it)
            val angle = random.nextFloat() * 2f * Math.PI.toFloat()
            val speed = 250f + random.nextFloat() * 450f
            Particle(
                initialX = 0.5f,
                initialY = 0.45f,
                speedX = kotlin.math.cos(angle) * speed,
                speedY = kotlin.math.sin(angle) * speed - 150f, // bias upward
                color = confettiColors[random.nextInt(confettiColors.size)],
                size = 14f + random.nextFloat() * 16f,
                rotationSpeed = (random.nextFloat() - 0.5f) * 720f
            )
        }
    }

    LaunchedEffect(Unit) {
        trigger = true
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        particles.forEach { p ->
            val startX = p.initialX * w
            val startY = p.initialY * h
            val currentX = startX + p.speedX * progress
            val gravity = 400f * progress * progress
            val currentY = startY + (p.speedY * progress) + gravity
            val alpha = (1f - progress).coerceIn(0f, 1f)

            rotate(degrees = p.rotationSpeed * progress, pivot = Offset(currentX, currentY)) {
                drawRect(
                    color = p.color.copy(alpha = alpha),
                    topLeft = Offset(currentX - p.size / 2f, currentY - p.size / 2f),
                    size = Size(p.size, p.size * 0.6f)
                )
            }
        }
    }
}
