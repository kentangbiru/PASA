package com.example.tugascapstonepasa.data.model

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.tugascapstonepasa.R
import com.example.tugascapstonepasa.databinding.FragmentHomeBinding

class UpdateStrengthBar(
    private val binding: FragmentHomeBinding,
    private val context: Context
) {

    // Fungsi untuk memperbarui progress bar dan teks berdasarkan kekuatan password
    fun update(strength: String?) {
        val safeStrength = strength?.lowercase() ?: "unknown"
        // Nilai untuk progress bar, warna, dan teks kekuatan
        val (strengthValue, color, strengthText) = when (safeStrength) {
            "weak" -> Triple(
                25,
                ContextCompat.getColor(context, android.R.color.holo_red_light),
                context.getString(R.string.weak_strength)
            )
            "medium" -> Triple(
                50,
                ContextCompat.getColor(context, android.R.color.holo_orange_light),
                context.getString(R.string.medium_strength)
            )
            "strong" -> Triple(
                100,
                ContextCompat.getColor(context, android.R.color.holo_green_light),
                context.getString(R.string.strong_strength)
            )
            else -> Triple(
                0,
                Color.GRAY,
                context.getString(R.string.unknown_strength)
            )
        }

        // Perbarui progress bar
        binding.strengthBar.progress = strengthValue
        binding.strengthBar.progressTintList = android.content.res.ColorStateList.valueOf(color)

        // Perbarui teks kekuatan
        binding.strengthText.text = strengthText
        Log.d("UpdateStrengthBar", "Strength Text Updated: $strengthText")
    }
}
