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
        value =
            "Kata sandi yang kuat penting untuk melindungi akun dari akses yang tidak sah. Kombinasi kata sandi yang kompleks dapat mencegah risiko peretasan, serangan brute force, dan pencurian identitas."
    }
    val whyStrongPassword: LiveData<String> = _whyStrongPassword

    private val _tips = MutableLiveData<List<String>>().apply {
        value = listOf(
            "Hindari menggunakan informasi pribadi seperti nama, ulang tahun, atau email sebagai kata sandi, karena mudah ditebak.",
            "Buat kata sandi yang panjang, minimal 6 karakter, atau lebih untuk keamanan ekstra.",
            "Gunakan kombinasi angka, simbol, huruf besar, dan huruf kecil.",
            "Jangan gunakan kata sandi yang sama di semua akun; ini bisa membahayakan akun lain jika satu akun diretas.",
            "Hindari kata-kata umum dari kamus, misalnya, 'berenang1' adalah kata sandi yang lemah.",
            "Perbarui kata sandi Anda secara berkala untuk mencegah akses tidak sah.",
            "Gunakan pengelola kata sandi (password manager) untuk membuat dan menyimpan kata sandi unik.",
            "Pastikan setiap akun memiliki kata sandi yang berbeda agar tidak saling terhubung.",
            "Aktifkan autentikasi dua faktor (2FA) untuk perlindungan tambahan.",
            "Hindari mencatat kata sandi di tempat yang mudah terlihat, seperti di kertas atau catatan elektronik tanpa perlindungan."
        )
    }
    val tips: LiveData<List<String>> = _tips

    private val _examples = MutableLiveData<List<Pair<String, String>>>().apply {
        value = listOf(
            "“Mei1990” atau “Surabaya123”" to "Kesalahan pada contoh di atas adalah penggunaan informasi pribadi, kurangnya kombinasi karakter.",
            "“P@ss1”" to "Kesalahan pada contoh di atas adalah panjang password yang terlalu pendek.",
            "“kataSandi123” atau “admin123”" to "Kesalahan pada contoh di atas adalah penggunaan kata yang umum dan kurang kombinasi karakter.",
            "“12345678” atau “qwerty123”" to "Contoh di atas menggunakan pola yang mudah ditebak, sehingga mudah diretas.",
            "“password” atau “1234”" to "Kesalahan di atas adalah menggunakan kata sandi yang sangat umum tanpa modifikasi."
        )
    }
    val examples: LiveData<List<Pair<String, String>>> = _examples

    private val _recommendation = MutableLiveData<String>().apply {
        value = """
        • Hindari informasi pribadi: Jangan gunakan nama, ulang tahun, atau informasi lain yang mudah ditebak.
        • Panjang minimal: Gunakan kata sandi dengan panjang minimal 12 karakter untuk keamanan ekstra.
        • Kombinasi karakter: Gabungkan huruf besar, huruf kecil, angka, dan simbol.
        • Hindari pengulangan: Jangan gunakan pola atau urutan yang mudah dikenali.
        • Gunakan frasa unik: Kombinasikan kata acak yang tidak saling berhubungan, seperti "P4sta!Hujan#Merah".
    """.trimIndent()
    }
    val recommendation: LiveData<String> = _recommendation
}