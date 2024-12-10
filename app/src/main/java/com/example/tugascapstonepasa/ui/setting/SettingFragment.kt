package com.example.tugascapstonepasa.ui.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tugascapstonepasa.databinding.FragmentSettingBinding
import androidx.appcompat.app.AppCompatDelegate

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Mengambil ViewModel (opsional jika Anda menggunakannya)
        val settingViewModel =
            ViewModelProvider(this).get(SettingViewModel::class.java)

        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        // Mengakses SharedPreferences untuk menyimpan preferensi tema
        sharedPreferences = requireContext().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

        // Mengambil status tema gelap dari SharedPreferences dan set switch
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
        binding.switchDarkMode.isChecked = isDarkMode
        setThemeMode(isDarkMode)

        // Set listener untuk perubahan tema ketika switch diubah
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Menyimpan preferensi tema ke SharedPreferences
            sharedPreferences.edit().putBoolean("isDarkMode", isChecked).apply()
            setThemeMode(isChecked)
        }

        return binding.root
    }

    // Fungsi untuk mengatur mode tema
    private fun setThemeMode(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
