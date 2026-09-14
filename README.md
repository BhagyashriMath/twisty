# 🌀 Twisty - Mobile Puzzle Game

**Twisty** is a vibrant, addictive, and casual puzzle game inspired by twisting, curved ribbon paths. Connect matching colored dots across 30 progressively challenging levels without crossing lines or hitting obstacle blocks!

---

## 🎮 How to Play
1. **Connect Matching Dots**: Drag your finger or mouse between two dots of the same color.
2. **Smooth Twisting Paths**: Watch the paths curve and twist smoothly.
3. **No Crossing**: Paths cannot cross each other or pass through different colored dots.
4. **Avoid Obstacles**: Steer around striped obstacle blocks in higher levels.
5. **Rewind & Hints**: Drag backward to erase loops, or spend coins to reveal hints when stuck!
6. **Win Stars**: Clear levels with optimal moves to earn 3 stars and coin rewards!

---

## 🚀 Play Online (GitHub Pages / Web)
You can play **Twisty** directly in your browser without installing anything:
👉 **[Play Twisty Online](https://YOUR_GITHUB_USERNAME.github.io/Twisty/web_preview/)**

---

## 📲 Install on Android Device (APK)
Download the ready-to-install Android package:
- 📥 **[Download Twisty-Game.apk](web_preview/Twisty-Game.apk)**

**To install on Android**:
1. Tap `Twisty-Game.apk` on your phone.
2. Tap **Settings** ➔ Turn on **"Allow from this source"**.
3. Tap **Install** ➔ **Open**!

---

## 🛠 Project Architecture
- **Language**: Kotlin 2.3
- **Framework**: Jetpack Compose & Android SDK 36 (minSdk 24)
- **Graphics**: Hardware-accelerated Canvas with glowing bezier-style twisting ribbons
- **Audio**: Custom zero-asset procedural 16-bit PCM synthesizer (`AudioTrack`) + Android `Vibrator` haptics
- **Persistence**: Local `SharedPreferences` for unlocked levels, star tallies, coins, and settings
- **Levels**: 30 handcrafted progressive puzzle levels in `LevelRepository.kt`

---

## 💻 Build from Source (VS Code / Android Studio)

### In VS Code:
1. Open this folder in VS Code.
2. Press `Ctrl + Shift + B` to build the debug APK.
3. Use `Terminal -> Run Task -> Install & Launch on Android Device` to install on a connected phone.

### In Android Studio:
1. Open Android Studio ➔ **Open** ➔ Select the `Twisty` folder.
2. Click the green **Run** ▶ button.
