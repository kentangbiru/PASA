package com.example.tugascapstonepasa.ui.splash



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugascapstonepasa.MainActivity
import com.example.tugascapstonepasa.R
import com.google.android.material.button.MaterialButton

class IntroductionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        val btnContinue: MaterialButton = findViewById(R.id.btn_continue)

        btnContinue.setOnClickListener {
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}
