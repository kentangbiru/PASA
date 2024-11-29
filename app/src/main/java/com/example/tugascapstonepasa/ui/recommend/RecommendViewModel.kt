package com.example.tugascapstonepasa.ui.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecommendViewModel : ViewModel() {

    private val _title = MutableLiveData<String>().apply {
        value = "Rekomendasi"
    }
    val title: LiveData<String> = _title

    private val _whyStrongPassword = MutableLiveData<String>().apply {
        value = "Mengapa password yang kuat dibutuhkan?"
    }
    val whyStrongPassword: LiveData<String> = _whyStrongPassword

    private val _tips = MutableLiveData<List<String>>().apply {
        value = listOf(
            "Hindari menggunakan informasi pribadi seperti nama, ulang tahun...",
            "Buat kata sandi yang panjang, minimal 6 karakter...",
            "Gunakan kombinasi angka, simbol, huruf besar, dan huruf kecil..."
        )
    }
    val tips: LiveData<List<String>> = _tips

    private val _examples = MutableLiveData<List<String>>().apply {
        value = listOf(
            "“Mei1990” atau “Surabaya123” - Menggunakan informasi pribadi.",
            "“P@ss1” - Terlalu pendek.",
            "“kataSandi123” - Kombinasi karakter kurang kuat."
        )
    }
    val examples: LiveData<List<String>> = _examples
}
