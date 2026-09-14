package com.example.twisty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twisty.data.levels.LevelRepository
import com.example.twisty.data.models.Level
import com.example.twisty.theme.DotBerryPink
import com.example.twisty.theme.DotEmerald
import com.example.twisty.theme.DotSkyCyan
import com.example.twisty.theme.GoldCoin
import com.example.twisty.theme.TextSecondary
import com.example.twisty.theme.TwistyCardBg
import com.example.twisty.theme.TwistyCardBorder
import com.example.twisty.ui.components.AnimatedBackground
import com.example.twisty.ui.components.StarRatingView

@Composable
fun LevelSelectScreen(
    unlockedLevel: Int,
    coins: Int,
    getStarsForLevel: (Int) -> Int,
    onLevelSelected: (Level) -> Unit,
    onBackToHome: () -> Unit
) {
    val allLevels = LevelRepository.levels

    AnimatedBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackToHome,
                    modifier = Modifier
                        .background(TwistyCardBg, shape = CircleShape)
                        .border(1.5.dp, TwistyCardBorder, shape = CircleShape)
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Text(
                    text = "SELECT LEVEL",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    letterSpacing = 1.sp
                )

                // Coin Badge
                Row(
                    modifier = Modifier
                        .background(TwistyCardBg, shape = RoundedCornerShape(20.dp))
                        .border(1.5.dp, TwistyCardBorder, shape = RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(GoldCoin, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "$coins",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Levels Grid (5 columns, 6 rows for 30 levels)
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(allLevels) { level ->
                    val isUnlocked = level.id <= unlockedLevel
                    val stars = getStarsForLevel(level.id)
                    val isCurrent = level.id == unlockedLevel

                    LevelCard(
                        levelId = level.id,
                        isUnlocked = isUnlocked,
                        isCurrent = isCurrent,
                        stars = stars,
                        onClick = {
                            if (isUnlocked) {
                                onLevelSelected(level)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LevelCard(
    levelId: Int,
    isUnlocked: Boolean,
    isCurrent: Boolean,
    stars: Int,
    onClick: () -> Unit
) {
    val borderBrush = when {
        isCurrent -> Brush.linearGradient(listOf(DotBerryPink, DotSkyCyan))
        stars == 3 -> Brush.linearGradient(listOf(GoldCoin, DotEmerald))
        isUnlocked -> Brush.linearGradient(listOf(TwistyCardBorder, TwistyCardBorder))
        else -> Brush.linearGradient(listOf(Color(0xFF22163E), Color(0xFF22163E)))
    }

    val cardBg = when {
        isUnlocked -> TwistyCardBg
        else -> Color(0xFF160E2E)
    }

    Box(
        modifier = Modifier
            .aspectRatio(0.9f)
            .clip(RoundedCornerShape(16.dp))
            .background(cardBg)
            .border(width = if (isCurrent || stars == 3) 2.5.dp else 1.dp, brush = borderBrush, shape = RoundedCornerShape(16.dp))
            .clickable(enabled = isUnlocked, onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isUnlocked) {
                Text(
                    text = "$levelId",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = if (isCurrent) DotSkyCyan else Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                StarRatingView(
                    stars = stars,
                    starSize = 13.dp,
                    animated = false
                )
            } else {
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = "Locked",
                    tint = TextSecondary.copy(alpha = 0.4f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$levelId",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary.copy(alpha = 0.4f)
                )
            }
        }
    }
}
