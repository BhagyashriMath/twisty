package com.example.twisty.data.repository

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("twisty_game_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_UNLOCKED_LEVEL = "key_unlocked_level"
        private const val KEY_COINS = "key_coins"
        private const val KEY_SOUND_ENABLED = "key_sound_enabled"
        private const val KEY_MUSIC_ENABLED = "key_music_enabled"
        private const val KEY_VIBRATION_ENABLED = "key_vibration_enabled"
        private const val PREFIX_LEVEL_STARS = "key_stars_level_"
        private const val PREFIX_LEVEL_COMPLETED = "key_completed_level_"
        private const val INITIAL_COINS = 50
    }

    var highestUnlockedLevel: Int
        get() = prefs.getInt(KEY_UNLOCKED_LEVEL, 1)
        set(value) = prefs.edit().putInt(KEY_UNLOCKED_LEVEL, value).apply()

    var coins: Int
        get() = prefs.getInt(KEY_COINS, INITIAL_COINS)
        set(value) = prefs.edit().putInt(KEY_COINS, value).apply()

    var soundEnabled: Boolean
        get() = prefs.getBoolean(KEY_SOUND_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_SOUND_ENABLED, value).apply()

    var musicEnabled: Boolean
        get() = prefs.getBoolean(KEY_MUSIC_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_MUSIC_ENABLED, value).apply()

    var vibrationEnabled: Boolean
        get() = prefs.getBoolean(KEY_VIBRATION_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_VIBRATION_ENABLED, value).apply()

    fun getStarsForLevel(levelId: Int): Int {
        return prefs.getInt("$PREFIX_LEVEL_STARS$levelId", 0)
    }

    fun isLevelCompleted(levelId: Int): Boolean {
        return prefs.getBoolean("$PREFIX_LEVEL_COMPLETED$levelId", false)
    }

    /**
     * Records a level completion, updates stars, coins, and unlocks the next level.
     * Returns the number of coins awarded.
     */
    fun recordLevelCompletion(levelId: Int, stars: Int): Int {
        val alreadyCompleted = isLevelCompleted(levelId)
        val prevStars = getStarsForLevel(levelId)

        val editor = prefs.edit()
        editor.putBoolean("$PREFIX_LEVEL_COMPLETED$levelId", true)

        if (stars > prevStars) {
            editor.putInt("$PREFIX_LEVEL_STARS$levelId", stars)
        }

        // Unlock next level
        val currentUnlocked = highestUnlockedLevel
        if (levelId >= currentUnlocked) {
            editor.putInt(KEY_UNLOCKED_LEVEL, levelId + 1)
        }

        // Calculate coin reward
        var coinsAwarded = 0
        if (!alreadyCompleted) {
            coinsAwarded += 20 // First time completion reward
            if (stars == 3) {
                coinsAwarded += 10 // 3-star bonus
            }
        } else if (stars > prevStars) {
            coinsAwarded += (stars - prevStars) * 5 // Improvement bonus
        }

        if (coinsAwarded > 0) {
            editor.putInt(KEY_COINS, coins + coinsAwarded)
        }

        editor.apply()
        return coinsAwarded
    }

    /**
     * Deduct coins for hint usage. Returns true if successful.
     */
    fun spendCoins(amount: Int): Boolean {
        val current = coins
        if (current >= amount) {
            coins = current - amount
            return true
        }
        return false
    }

    /**
     * Resets all player progress back to default.
     */
    fun resetProgress() {
        prefs.edit().clear().apply()
    }
}
