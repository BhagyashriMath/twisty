package com.example.twisty.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.twisty.theme.DotBerryPink
import com.example.twisty.theme.DotCoral
import com.example.twisty.theme.DotSkyCyan
import com.example.twisty.theme.TextSecondary
import com.example.twisty.theme.TwistyCardBg
import com.example.twisty.theme.TwistyCardBorder

@Composable
fun SettingsDialog(
    soundEnabled: Boolean,
    musicEnabled: Boolean,
    vibrationEnabled: Boolean,
    onToggleSound: (Boolean) -> Unit,
    onToggleMusic: (Boolean) -> Unit,
    onToggleVibration: (Boolean) -> Unit,
    onResetProgress: () -> Unit,
    onDismiss: () -> Unit
) {
    var showResetConfirm by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = TwistyCardBg, shape = RoundedCornerShape(24.dp))
                .border(width = 2.dp, color = TwistyCardBorder, shape = RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SETTINGS",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )

                IconButton(onClick = onDismiss) {
                    Icon(Icons.Rounded.Close, contentDescription = "Close", tint = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sound FX Toggle
            SettingsToggleRow(
                title = "Sound Effects",
                subtitle = "Pops, chimes, and win fanfare",
                checked = soundEnabled,
                onCheckedChange = onToggleSound
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ambient Music Toggle
            SettingsToggleRow(
                title = "Ambient Chimes",
                subtitle = "Soothing background melody",
                checked = musicEnabled,
                onCheckedChange = onToggleMusic
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Vibration Toggle
            SettingsToggleRow(
                title = "Haptic Vibration",
                subtitle = "Subtle touch feedback",
                checked = vibrationEnabled,
                onCheckedChange = onToggleVibration
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Reset Progress
            OutlinedButton(
                onClick = { showResetConfirm = true },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = DotCoral)
            ) {
                Icon(Icons.Rounded.Delete, contentDescription = "Reset")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reset All Progress", fontWeight = FontWeight.Bold)
            }
        }
    }

    if (showResetConfirm) {
        AlertDialog(
            onDismissRequest = { showResetConfirm = false },
            title = { Text("Reset Progress?", fontWeight = FontWeight.Bold) },
            text = { Text("This will reset all your unlocked levels, stars, and coins. This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        showResetConfirm = false
                        onResetProgress()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DotCoral)
                ) {
                    Text("Reset Everything")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirm = false }) {
                    Text("Cancel")
                }
            },
            containerColor = TwistyCardBg
        )
    }
}

@Composable
private fun SettingsToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = subtitle, fontSize = 12.sp, color = TextSecondary)
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = DotSkyCyan,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color(0xFF1B1238)
            )
        )
    }
}
