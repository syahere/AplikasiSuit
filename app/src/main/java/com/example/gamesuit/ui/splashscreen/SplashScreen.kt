package com.example.gamesuit.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.example.gamesuit.ui.main.MainActivity
import com.example.gamesuit.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.let {
            Glide.with(it)
                .load("https://github.com/syahere/assets/blob/master/splash_screen1.png?raw=true")
                .into(binding.ivSplash)
        }

        Handler (Looper.getMainLooper()).postDelayed({
            Intent(this, MainActivity::class.java)
                .apply {
                    startActivity(this)
                }
            finish()
        }, 3000)
    }
}

