package com.example.twisty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.twisty.data.levels.LevelRepository
import com.example.twisty.data.models.Level
import com.example.twisty.data.repository.UserPreferences
import com.example.twisty.game.audio.SoundManager
import com.example.twisty.theme.TwistyBgDark
import com.example.twisty.theme.TwistyTheme
import com.example.twisty.ui.dialogs.SettingsDialog
import com.example.twisty.ui.screens.GameScreen
import com.example.twisty.ui.screens.HomeScreen
import com.example.twisty.ui.screens.LevelSelectScreen

sealed class AppScreen {
    data object Home : AppScreen()
    data object LevelSelect : AppScreen()
    data class Game(val level: Level) : AppScreen()
}

class MainActivity : ComponentActivity() {

    private lateinit var soundManager: SoundManager
    private lateinit var preferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        preferences = UserPreferences(this)
        soundManager = SoundManager(this, preferences)

        setContent {
            TwistyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = TwistyBgDark
                ) {
                    TwistyApp(preferences = preferences, soundManager = soundManager)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::soundManager.isInitialized) {
            soundManager.startAmbientMusic()
        }
    }

    override fun onPause() {
        super.onPause()
        if (::soundManager.isInitialized) {
            soundManager.stopAmbientMusic()
        }
    }
}

@Composable
fun TwistyApp(
    preferences: UserPreferences,
    soundManager: SoundManager
) {
    var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Home) }
    var unlockedLevel by remember { mutableIntStateOf(preferences.highestUnlockedLevel) }
    var coins by remember { mutableIntStateOf(preferences.coins) }
    var showSettings by remember { mutableStateOf(false) }

    // Settings state
    var soundEnabled by remember { mutableStateOf(preferences.soundEnabled) }
    var musicEnabled by remember { mutableStateOf(preferences.musicEnabled) }
    var vibrationEnabled by remember { mutableStateOf(preferences.vibrationEnabled) }

    // Audio lifecycle management
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, musicEnabled) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    if (musicEnabled) soundManager.startAmbientMusic()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    soundManager.stopAmbientMusic()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    when (val screen = currentScreen) {
        is AppScreen.Home -> {
            HomeScreen(
                coins = coins,
                currentLevel = unlockedLevel,
                onPlayClick = {
                    soundManager.playButtonClick()
                    val targetLevel = LevelRepository.getLevel(unlockedLevel)
                    currentScreen = AppScreen.Game(targetLevel)
                },
                onLevelsClick = {
                    soundManager.playButtonClick()
                    currentScreen = AppScreen.LevelSelect
                },
                onSettingsClick = {
                    soundManager.playButtonClick()
                    showSettings = true
                }
            )
        }

        is AppScreen.LevelSelect -> {
            BackHandler {
                currentScreen = AppScreen.Home
            }
            LevelSelectScreen(
                unlockedLevel = unlockedLevel,
                coins = coins,
                getStarsForLevel = { levelId -> preferences.getStarsForLevel(levelId) },
                onLevelSelected = { level ->
                    soundManager.playButtonClick()
                    currentScreen = AppScreen.Game(level)
                },
                onBackToHome = {
                    soundManager.playButtonClick()
                    currentScreen = AppScreen.Home
                }
            )
        }

        is AppScreen.Game -> {
            BackHandler {
                currentScreen = AppScreen.LevelSelect
            }
            GameScreen(
                level = screen.level,
                coins = coins,
                soundManager = soundManager,
                onSpendCoins = { amount ->
                    val success = preferences.spendCoins(amount)
                    if (success) {
                        coins = preferences.coins
                    }
                    success
                },
                onLevelCompleted = { levelId, stars ->
                    val awarded = preferences.recordLevelCompletion(levelId, stars)
                    unlockedLevel = preferences.highestUnlockedLevel
                    coins = preferences.coins
                    awarded
                },
                onNextLevel = { nextLevel ->
                    soundManager.playButtonClick()
                    currentScreen = AppScreen.Game(nextLevel)
                },
                onBackToLevelSelect = {
                    soundManager.playButtonClick()
                    currentScreen = AppScreen.LevelSelect
                },
                onOpenSettings = {
                    soundManager.playButtonClick()
                    showSettings = true
                }
            )
        }
    }

    if (showSettings) {
        SettingsDialog(
            soundEnabled = soundEnabled,
            musicEnabled = musicEnabled,
            vibrationEnabled = vibrationEnabled,
            onToggleSound = { enabled ->
                soundEnabled = enabled
                preferences.soundEnabled = enabled
            },
            onToggleMusic = { enabled ->
                musicEnabled = enabled
                preferences.musicEnabled = enabled
                if (enabled) soundManager.startAmbientMusic() else soundManager.stopAmbientMusic()
            },
            onToggleVibration = { enabled ->
                vibrationEnabled = enabled
                preferences.vibrationEnabled = enabled
            },
            onResetProgress = {
                preferences.resetProgress()
                unlockedLevel = preferences.highestUnlockedLevel
                coins = preferences.coins
            },
            onDismiss = {
                showSettings = false
            }
        )
    }
}
