package com.example.twisty.game.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import com.example.twisty.data.repository.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

/**
 * High-performance, zero-asset procedural audio & haptics engine.
 * Generates pure PCM audio waveforms in-memory, ensuring instant playback
 * without needing external audio files.
 */
class SoundManager(
    private val context: Context,
    private val preferences: UserPreferences
) {
    private val sampleRate = 22050
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private var musicJob: Job? = null

    private val vibrator: Vibrator? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            manager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    }

    // Pre-generated PCM sound buffers
    private val clickBuffer: ShortArray by lazy { generateClickSound() }
    private val popBuffer: ShortArray by lazy { generatePopSound() }
    private val stepBuffer: ShortArray by lazy { generateStepSound() }
    private val connectBuffer: ShortArray by lazy { generateConnectChime() }
    private val invalidBuffer: ShortArray by lazy { generateInvalidThud() }
    private val winBuffer: ShortArray by lazy { generateWinFanfare() }
    private val coinBuffer: ShortArray by lazy { generateCoinChime() }

    fun playButtonClick() {
        if (!preferences.soundEnabled) return
        playSound(clickBuffer)
        vibrate(20)
    }

    fun playDotTouch() {
        if (!preferences.soundEnabled) return
        playSound(popBuffer)
        vibrate(25)
    }

    fun playPathStep() {
        if (!preferences.soundEnabled) return
        playSound(stepBuffer)
    }

    fun playPairConnected() {
        if (!preferences.soundEnabled) return
        playSound(connectBuffer)
        vibrate(45)
    }

    fun playInvalidMove() {
        if (!preferences.soundEnabled) return
        playSound(invalidBuffer)
        vibrate(70)
    }

    fun playLevelComplete() {
        if (!preferences.soundEnabled) return
        playSound(winBuffer)
        vibratePattern(longArrayOf(0, 50, 50, 100))
    }

    fun playCoinReward() {
        if (!preferences.soundEnabled) return
        playSound(coinBuffer)
        vibrate(30)
    }

    fun startAmbientMusic() {
        if (!preferences.musicEnabled || musicJob?.isActive == true) return

        musicJob = coroutineScope.launch {
            // Soothing pentatonic chord progression: C, G, Am, F
            val pentatonicPitches = listOf(261.63, 329.63, 392.00, 523.25, 659.25)
            var index = 0
            while (isActive && preferences.musicEnabled) {
                val freq = pentatonicPitches[index % pentatonicPitches.size]
                val tone = generateSoftTone(freq, 0.45, 0.12f)
                playSound(tone)
                delay(950)
                index++
            }
        }
    }

    fun stopAmbientMusic() {
        musicJob?.cancel()
        musicJob = null
    }

    private fun playSound(pcmData: ShortArray) {
        coroutineScope.launch {
            try {
                val track = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(pcmData.size * 2)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()

                track.write(pcmData, 0, pcmData.size)
                track.play()

                // Release after playback finishes
                val durationMs = (pcmData.size * 1000L) / sampleRate
                delay(durationMs + 50)
                track.stop()
                track.release()
            } catch (_: Exception) {
                // Ignore audio hardware errors gracefully
            }
        }
    }

    private fun vibrate(durationMs: Long) {
        if (!preferences.vibrationEnabled) return
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(durationMs)
            }
        } catch (_: Exception) {}
    }

    private fun vibratePattern(pattern: LongArray) {
        if (!preferences.vibrationEnabled) return
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(pattern, -1)
            }
        } catch (_: Exception) {}
    }

    // PCM Synthesis Algorithms
    private fun generateClickSound(): ShortArray {
        val duration = 0.035
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val envelope = 1.0 - (i.toDouble() / samples)
            val wave = sin(2.0 * PI * 1200.0 * t)
            buffer[i] = (wave * envelope * Short.MAX_VALUE * 0.4).toInt().toShort()
        }
        return buffer
    }

    private fun generatePopSound(): ShortArray {
        val duration = 0.06
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val freq = 440.0 + (t / duration) * 330.0 // sweep up 440 -> 770
            val envelope = (1.0 - (i.toDouble() / samples))
            val wave = sin(2.0 * PI * freq * t)
            buffer[i] = (wave * envelope * Short.MAX_VALUE * 0.5).toInt().toShort()
        }
        return buffer
    }

    private fun generateStepSound(): ShortArray {
        val duration = 0.02
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val envelope = 1.0 - (i.toDouble() / samples)
            val wave = sin(2.0 * PI * 850.0 * t)
            buffer[i] = (wave * envelope * Short.MAX_VALUE * 0.25).toInt().toShort()
        }
        return buffer
    }

    private fun generateConnectChime(): ShortArray {
        val duration = 0.22
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val freq = if (t < 0.10) 523.25 else 659.25 // C5 then E5
            val envelope = 1.0 - (i.toDouble() / samples)
            val wave = sin(2.0 * PI * freq * t) + 0.3 * sin(2.0 * PI * freq * 2 * t)
            buffer[i] = (wave * envelope * Short.MAX_VALUE * 0.45).toInt().toShort()
        }
        return buffer
    }

    private fun generateInvalidThud(): ShortArray {
        val duration = 0.12
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val freq = 140.0 - (t / duration) * 50.0 // Pitch drop
            val envelope = 1.0 - (i.toDouble() / samples)
            val wave = sin(2.0 * PI * freq * t)
            buffer[i] = (wave * envelope * Short.MAX_VALUE * 0.4).toInt().toShort()
        }
        return buffer
    }

    private fun generateWinFanfare(): ShortArray {
        val duration = 0.65
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        val noteLength = 0.14
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val freq = when {
                t < noteLength -> 523.25       // C5
                t < noteLength * 2 -> 659.25   // E5
                t < noteLength * 3 -> 783.99   // G5
                else -> 1046.50                // C6
            }
            val noteT = t % noteLength
            val noteEnv = 1.0 - (noteT / noteLength)
            val overallEnv = 1.0 - (t / duration) * 0.4
            val wave = sin(2.0 * PI * freq * t) + 0.25 * sin(2.0 * PI * freq * 2 * t)
            buffer[i] = (wave * noteEnv * overallEnv * Short.MAX_VALUE * 0.45).toInt().toShort()
        }
        return buffer
    }

    private fun generateCoinChime(): ShortArray {
        val duration = 0.25
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val freq = if (t < 0.10) 987.77 else 1318.51 // B5 -> E6
            val envelope = 1.0 - (i.toDouble() / samples)
            val wave = sin(2.0 * PI * freq * t)
            buffer[i] = (wave * envelope * Short.MAX_VALUE * 0.4).toInt().toShort()
        }
        return buffer
    }

    private fun generateSoftTone(freq: Double, duration: Double, gain: Float): ShortArray {
        val samples = (sampleRate * duration).toInt()
        val buffer = ShortArray(samples)
        for (i in 0 until samples) {
            val t = i.toDouble() / sampleRate
            val envelope = sin(PI * (i.toDouble() / samples)) // Bell curve
            val wave = sin(2.0 * PI * freq * t)
            buffer[i] = (wave * envelope * Short.MAX_VALUE * gain).toInt().toShort()
        }
        return buffer
    }
}
