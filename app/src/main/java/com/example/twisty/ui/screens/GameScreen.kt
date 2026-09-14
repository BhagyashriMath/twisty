package com.example.twisty.ui.screens

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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twisty.data.levels.LevelRepository
import com.example.twisty.data.models.Level
import com.example.twisty.game.audio.SoundManager
import com.example.twisty.game.logic.GameActionResult
import com.example.twisty.game.logic.TwistyGameEngine
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
import com.example.twisty.ui.components.TwistyBoardCanvas
import com.example.twisty.ui.dialogs.LevelCompleteDialog
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    level: Level,
    coins: Int,
    soundManager: SoundManager,
    onSpendCoins: (Int) -> Boolean,
    onLevelCompleted: (levelId: Int, stars: Int) -> Int,
    onNextLevel: (Level) -> Unit,
    onBackToLevelSelect: () -> Unit,
    onOpenSettings: () -> Unit
) {
    val engine = remember(level.id) { TwistyGameEngine(level) }
    var gameState by remember(level.id) { mutableStateOf(engine.state) }
    var coinsEarnedThisRound by remember(level.id) { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(gameState.isCompleted) {
        if (gameState.isCompleted && coinsEarnedThisRound == 0) {
            soundManager.playLevelComplete()
            val earned = onLevelCompleted(level.id, gameState.starsEarned)
            coinsEarnedThisRound = earned
        }
    }

    AnimatedBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 1. Top HUD
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackToLevelSelect,
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

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "LEVEL ${level.id}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = level.title.substringAfter("- ").trim(),
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Coin badge
                        Row(
                            modifier = Modifier
                                .background(TwistyCardBg, shape = RoundedCornerShape(20.dp))
                                .border(1.5.dp, TwistyCardBorder, shape = RoundedCornerShape(20.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp),
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
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        IconButton(
                            onClick = onOpenSettings,
                            modifier = Modifier
                                .background(TwistyCardBg, shape = CircleShape)
                                .border(1.5.dp, TwistyCardBorder, shape = CircleShape)
                                .size(38.dp)
                        ) {
                            Icon(
                                Icons.Rounded.Settings,
                                contentDescription = "Settings",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // 2. Sub-Bar: Moves & Par Rating
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Moves Pill
                    Row(
                        modifier = Modifier
                            .background(TwistyCardBg.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
                            .border(1.dp, TwistyCardBorder, RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Moves: ",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                        Text(
                            text = if (level.moveLimit > 0) "${gameState.movesCount} / ${level.moveLimit}" else "${gameState.movesCount}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (level.moveLimit > 0 && gameState.movesCount >= level.moveLimit) DotBerryPink else Color.White
                        )
                    }

                    // Connections Progress
                    val connectedCount = level.pairs.count { gameState.connections[it.pairId]?.isConnected == true }
                    Row(
                        modifier = Modifier
                            .background(TwistyCardBg.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
                            .border(1.dp, TwistyCardBorder, RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pairs: ",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                        Text(
                            text = "$connectedCount / ${level.pairs.size}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (connectedCount == level.pairs.size) DotEmerald else DotSkyCyan
                        )
                    }
                }

                // 3. Central Game Board Canvas
                TwistyBoardCanvas(
                    gameState = gameState,
                    onCellTouchStart = { coord ->
                        val result = engine.onTouchStart(coord)
                        gameState = engine.state
                        when (result) {
                            is GameActionResult.DotTouched -> soundManager.playDotTouch()
                            is GameActionResult.PathRewound -> soundManager.playPathStep()
                            else -> {}
                        }
                    },
                    onCellTouchMove = { coord ->
                        val result = engine.onTouchMove(coord)
                        gameState = engine.state
                        when (result) {
                            is GameActionResult.PathExtended -> soundManager.playPathStep()
                            is GameActionResult.PathRewound -> soundManager.playPathStep()
                            is GameActionResult.PairConnected -> soundManager.playPairConnected()
                            is GameActionResult.InvalidMove -> soundManager.playInvalidMove()
                            is GameActionResult.LevelCompleted -> soundManager.playLevelComplete()
                            else -> {}
                        }
                    },
                    onCellTouchEnd = {
                        engine.onTouchEnd()
                        gameState = engine.state
                    },
                    modifier = Modifier.weight(1f, fill = false)
                )

                // 4. Bottom Action Bar (Reset & Hint)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Reset Button
                    OutlinedButton(
                        onClick = {
                            soundManager.playButtonClick()
                            engine.reset()
                            gameState = engine.state
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, TwistyCardBorder)
                    ) {
                        Icon(Icons.Rounded.Refresh, contentDescription = "Reset")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "RESET",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Hint Button (Costs 25 coins)
                    Button(
                        onClick = {
                            val hintCost = 25
                            if (coins >= hintCost) {
                                if (onSpendCoins(hintCost)) {
                                    val hintPair = engine.applyHint()
                                    if (hintPair != null) {
                                        gameState = engine.state
                                        soundManager.playPairConnected()
                                    } else {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("All pairs already connected!")
                                        }
                                    }
                                }
                            } else {
                                soundManager.playInvalidMove()
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Need $hintCost coins for a hint! Clear levels to earn more.")
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1.3f)
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DotRoyalPurple),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(GoldCoin, shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "HINT (25)",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 72.dp)
            )

            // 5. Level Complete Modal Overlay
            if (gameState.isCompleted) {
                val hasNext = level.id < LevelRepository.levels.size
                LevelCompleteDialog(
                    levelId = level.id,
                    stars = gameState.starsEarned,
                    moves = gameState.movesCount,
                    coinsEarned = coinsEarnedThisRound,
                    hasNextLevel = hasNext,
                    onNextLevel = {
                        val next = LevelRepository.getLevel(level.id + 1)
                        onNextLevel(next)
                    },
                    onReplay = {
                        coinsEarnedThisRound = 0
                        engine.reset()
                        gameState = engine.state
                    },
                    onHome = onBackToLevelSelect
                )
            }
        }
    }
}
