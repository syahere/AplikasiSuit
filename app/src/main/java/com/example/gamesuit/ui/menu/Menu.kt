package com.example.gamesuit.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesuit.ui.game.GameActivity
import com.example.gamesuit.databinding.ActivityMenuBinding
import com.example.gamesuit.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class Menu : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding
    private lateinit var playerName: String

    companion object{
        const val name=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
    }

    override fun onStart() {
        super.onStart()
        showSnackBar()
        startGame()
    }

    private fun startGame() {
        val intent = Intent(MainActivity@this, GameActivity::class.java)

        binding.tvComp.text= "$playerName vs Computer"
        binding.tvFriend.text = "$playerName vs Player 2"
        binding.ivFriend.setOnClickListener {
            intent.putExtra(GameActivity.player,"$playerName")
            intent.putExtra(GameActivity.rival,"Player 2")
            startActivity(intent)
        }

        binding.ivComp.setOnClickListener {
            intent.putExtra(GameActivity.player,"$playerName")
            intent.putExtra(GameActivity.rival,"Computer")
            startActivity(intent)
        }
    }


    private fun showSnackBar() {
        val snackbar = Snackbar.make(binding.root,"Selamat Datang $playerName", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup") {
            snackbar.dismiss()
        }
        snackbar.show()
    }



    private fun getIntentData() {
        playerName=intent.getStringExtra(name).toString()
    }
}