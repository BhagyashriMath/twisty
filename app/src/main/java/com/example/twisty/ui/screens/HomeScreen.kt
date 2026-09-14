package com.example.twisty.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twisty.theme.ButtonPlayGreen
import com.example.twisty.theme.DotBerryPink
import com.example.twisty.theme.DotEmerald
import com.example.twisty.theme.DotLemon
import com.example.twisty.theme.DotRoyalPurple
import com.example.twisty.theme.DotSkyCyan
import com.example.twisty.theme.DotTangerine
import com.example.twisty.theme.GoldCoin
import com.example.twisty.theme.TextSecondary
import com.example.twisty.theme.TwistyCardBg
import com.example.twisty.theme.TwistyCardBorder
import com.example.twisty.ui.components.AnimatedBackground

@Composable
fun HomeScreen(
    coins: Int,
    currentLevel: Int,
    onPlayClick: () -> Unit,
    onLevelsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "PlayButtonPulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseScale"
    )

    AnimatedBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar: Coins & Settings
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Coins badge
                Row(
                    modifier = Modifier
                        .background(TwistyCardBg, shape = RoundedCornerShape(20.dp))
                        .border(1.5.dp, TwistyCardBorder, shape = RoundedCornerShape(20.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(GoldCoin, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "$coins",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Settings button
                IconButton(
                    onClick = onSettingsClick,
                    modifier = Modifier
                        .background(TwistyCardBg, shape = CircleShape)
                        .border(1.5.dp, TwistyCardBorder, shape = CircleShape)
                ) {
                    Icon(
                        Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        tint = Color.White
                    )
                }
            }

            // Center Logo & Tagline
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Twisty Colorful Title with custom styled letters
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val letters = listOf(
                        Pair("T", DotBerryPink),
                        Pair("W", DotTangerine),
                        Pair("I", DotLemon),
                        Pair("S", DotEmerald),
                        Pair("T", DotSkyCyan),
                        Pair("Y", DotRoyalPurple)
                    )
                    letters.forEach { (char, color) ->
                        Text(
                            text = char,
                            fontSize = 54.sp,
                            fontWeight = FontWeight.Black,
                            color = color,
                            letterSpacing = 2.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Twist, Curve & Connect!",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Resume Level Indicator pill
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(DotBerryPink.copy(alpha = 0.2f), DotSkyCyan.copy(alpha = 0.2f))
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(1.dp, DotSkyCyan.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Current Stage: Level $currentLevel",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            // Bottom Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Big Pulsing PLAY Button
                Button(
                    onClick = onPlayClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .scale(pulseScale),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonPlayGreen),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.PlayArrow,
                            contentDescription = "Play",
                            modifier = Modifier.size(36.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "PLAY",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = 2.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // SELECT LEVEL Button
                Button(
                    onClick = onLevelsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TwistyCardBg),
                    border = androidx.compose.foundation.BorderStroke(
                        2.dp,
                        Brush.horizontalGradient(listOf(DotSkyCyan, DotRoyalPurple))
                    )
                ) {
                    Text(
                        text = "LEVELS (1 - 30)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
