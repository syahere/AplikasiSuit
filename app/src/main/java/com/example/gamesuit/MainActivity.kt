package com.example.gamesuit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var gameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameButton=findViewById(R.id.game_button)

        gameButton.setOnClickListener{
            val intent = Intent(MainActivity@this, GameActivity::class.java)
            startActivity(intent)
        }


    }
}