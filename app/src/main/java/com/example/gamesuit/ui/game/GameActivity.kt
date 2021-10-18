package com.example.gamesuit.ui.game

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivityGameBinding
import com.example.gamesuit.databinding.CustomDialogBinding
import com.example.gamesuit.element.StringContainer.paper
import com.example.gamesuit.element.StringContainer.rock
import com.example.gamesuit.element.StringContainer.scissors
import com.example.gamesuit.ui.menu.Menu
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var dialogBinding: CustomDialogBinding
    private lateinit var playerName: String
    private lateinit var rivalType: String
    private lateinit var mySuit: String
    private lateinit var enemyChoice: String
    private lateinit var winner: String
    val listElement = arrayOf(paper, rock, scissors)

    companion object {
        const val rival = "rivalType"
        const val player = "playerName"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        closeGame()

        playerChoice()


    }


        private fun playerChoice()  {
        binding.kertasLeft.setOnClickListener {
            binding.kertasLeft.onBoxClicked(binding.kertasLeft)
            binding.batuLeft.isEnabled = false
            binding.guntingLeft.isEnabled = false
            mySuit = "paper"
            enemyCheck()


        }
        binding.batuLeft.setOnClickListener {
            binding.batuLeft.onBoxClicked(binding.batuLeft)
            binding.kertasLeft.isEnabled = false
            binding.guntingLeft.isEnabled = false
            mySuit = "rock"
            enemyCheck()


        }
        binding.guntingLeft.setOnClickListener {
            binding.guntingLeft.onBoxClicked(binding.guntingLeft)
            binding.batuLeft.isEnabled = false
            binding.kertasLeft.isEnabled = false
            mySuit = "scissors"
            enemyCheck()

        }
    }



    private fun closeGame() {
        binding.ivClose.setOnClickListener {
            finish()
            exitProcess(-1)
        }
    }

    private fun getIntentData() {

        playerName = intent.getStringExtra(player).toString()
        rivalType = intent.getStringExtra(rival).toString()
        binding.tvPlayer1.text = playerName
        binding.tvRival.text = rivalType
    }

    private fun ImageView.onBoxClicked(image: ImageView?) {
        image?.setBackgroundColor(Color.parseColor("#ff0000"))
    }

    private fun ImageView.offBoxClicked(image: ImageView?) {
        image?.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
    }

    private fun enemyCheck() {
        if (rivalType == "Computer") {
            enemyChoice = listElement.random()
            when (enemyChoice) {
                "scissors" -> binding.guntingRight.onBoxClicked(binding.guntingRight)
                "rock" -> binding.batuRight.onBoxClicked(binding.batuRight)
                "paper" -> binding.kertasRight.onBoxClicked(binding.kertasRight)
            }
            resultSuit(mySuit)
            showDialog()
        }else {
            val snackbar2 = Snackbar.make(
                binding.root, "$playerName telah memilih. " +
                        "\n Silahkan $rivalType untuk memilih",
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar2.setAction("Close") {
                snackbar2.dismiss()
            }
            snackbar2.show()



            binding.batuRight.setOnClickListener {
                enemyChoice = "rock"
                binding.batuRight.onBoxClicked(binding.batuRight)
                resultSuit(mySuit)
                showDialog()

            }
            binding.guntingRight.setOnClickListener {
                enemyChoice = "scissors"
                binding.guntingRight.onBoxClicked(binding.guntingRight)
                resultSuit(mySuit)
                showDialog()

            }
            binding.kertasRight.setOnClickListener {
                enemyChoice = "paper"
                binding.kertasRight.onBoxClicked(binding.kertasRight)
                resultSuit(mySuit)
                showDialog()

            }
        }
    }

    private fun showDialog() {
        dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .apply {
                setView(dialogBinding.root)
            }
            .create()

        if (winner=="draw"){
            dialogBinding.tvWin.isVisible=false
            dialogBinding.tvWinner.text="SERI"
        }else{
            dialogBinding.tvWin.isVisible=true
            dialogBinding.tvWinner.text=winner
        }
        dialogBinding.btnPlayAgain.setOnClickListener{
            dialog.dismiss()
            clearChoice()
        }
        dialogBinding.btnBacktoMenu.setOnClickListener{
            val intent= Intent(this, Menu::class.java)
            intent.putExtra(Menu.name,playerName)
            startActivity(intent)
        }
        dialog.show()
    }

    private fun clearChoice() {
            binding.batuLeft.isEnabled = true
            binding.kertasLeft.isEnabled = true
            binding.guntingLeft.isEnabled = true
            binding.kertasLeft.offBoxClicked(binding.kertasLeft)
            binding.guntingLeft.offBoxClicked(binding.guntingLeft)
            binding.batuLeft.offBoxClicked(binding.batuLeft)
            binding.kertasRight.offBoxClicked(binding.kertasRight)
            binding.guntingRight.offBoxClicked(binding.guntingRight)
            binding.batuRight.offBoxClicked(binding.batuRight)
    }


    private fun resultSuit(mySuit:String) {
        when(mySuit){
            "scissors"-> {
                when (enemyChoice) {
                    "scissors" -> winner = "draw"
                    "paper" -> winner = playerName
                    "rock" -> winner = rivalType
                }
            }
            "paper" ->{
                when(enemyChoice){
                    "scissors"-> winner= rivalType
                    "paper" -> winner="draw"
                    "rock" -> winner = playerName
                }
            }
            "rock"->{
                when(enemyChoice){
                    "scissors"-> winner = playerName
                    "paper" -> winner = rivalType
                    "rock" -> winner = "draw"
                }
            }
        }
    }

    private fun showPlayerChoice() {
        Toast.makeText(this, "$playerName memilih $mySuit\n " +
                "$rivalType memilih $enemyChoice", Toast.LENGTH_SHORT).show()
    }
}










//    private fun playAgain() {
//        binding.play.setOnClickListener {
//            binding.batuLeft.isEnabled = true
//            binding.kertasLeft.isEnabled = true
//            binding.guntingLeft.isEnabled = true
//            binding.kertasLeft.offBoxClicked(binding.kertasLeft)
//            binding.guntingLeft.offBoxClicked(binding.guntingLeft)
//            binding.batuLeft.offBoxClicked(binding.batuLeft)
//            binding.kertasRight.offBoxClicked(binding.kertasRight)
//            binding.guntingRight.offBoxClicked(binding.guntingRight)
//            binding.batuRight.offBoxClicked(binding.batuRight)
//        }
//    }
//
//    private fun newGame() {
//        binding.newgame.setOnClickListener {
//            binding.batuLeft.isEnabled = true
//            binding.kertasLeft.isEnabled = true
//            binding.guntingLeft.isEnabled = true
//            binding.kertasLeft.offBoxClicked(binding.kertasLeft)
//            binding.guntingLeft.offBoxClicked(binding.guntingLeft)
//            binding.batuLeft.offBoxClicked(binding.batuLeft)
//            binding.kertasRight.offBoxClicked(binding.kertasRight)
//            binding.guntingRight.offBoxClicked(binding.guntingRight)
//            binding.batuRight.offBoxClicked(binding.batuRight)
//            binding.tvScorePlayer.text = "Player = 0"
//            binding.tvScoreRival.text = "Computer = 0"
//            scComp=0
//            scPlayer=0
//
//        }















