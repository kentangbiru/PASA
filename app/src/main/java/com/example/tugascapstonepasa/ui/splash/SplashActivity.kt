package com.example.tugascapstonepasa.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper
import com.example.tugascapstonepasa.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            goToIntroductionActivity()
        }, 2000)
    }

    private fun goToIntroductionActivity() {
        val intent = Intent(this, IntroductionActivity::class.java)
        startActivity(intent)
        finish()
    }
}
