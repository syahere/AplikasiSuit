package com.example.gamesuit

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesuit.element.StringContainer.paper
import com.example.gamesuit.element.StringContainer.rock
import com.example.gamesuit.element.StringContainer.scissors

class GameActivity : AppCompatActivity() {

    private val kertas_right: ImageView by lazy {
        findViewById(R.id.kertas_right)
    }
    private val batu_right: ImageView by lazy {
        findViewById(R.id.batu_right)
    }
    private val gunting_right: ImageView by lazy {
        findViewById(R.id.gunting_right)
    }
    private val kertas_left: ImageView by lazy {
        findViewById(R.id.kertas_left)
    }
    private val batu_left: ImageView by lazy {
        findViewById(R.id.batu_left)
    }
    private val gunting_left: ImageView by lazy {
        findViewById(R.id.gunting_left)
    }
    private val newGameButton: ImageView by lazy {
        findViewById(R.id.newgame)
    }
    private val midImage: ImageView by lazy {
        findViewById(R.id.result)
    }
    private val scorePlayer: TextView by lazy {
        findViewById(R.id.scoreplayer)
    }
    private val scoreComp: TextView by lazy {
        findViewById(R.id.scorecomp)
    }
    private val playAgainButton: Button by lazy {
        findViewById(R.id.play_again)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        kertas_left.setOnClickListener {
            kertas_left.onBoxClicked(kertas_left)
            batu_left.isEnabled = false
            gunting_left.isEnabled = false
            val mySuit = "paper"
            startSuit(mySuit)
            playAgain()
            newGame()

        }
        batu_left.setOnClickListener {
            batu_left.onBoxClicked(batu_left)
            kertas_left.isEnabled = false
            gunting_left.isEnabled = false
            val mySuit = "rock"
            startSuit(mySuit)
            playAgain()
            newGame()

        }
        gunting_left.setOnClickListener {
            gunting_left.onBoxClicked(gunting_left)
            batu_left.isEnabled = false
            kertas_left.isEnabled = false
            val mySuit = "scissors"
            startSuit(mySuit)
            playAgain()
            newGame()

        }
    }

    private fun ImageView.onBoxClicked(image: ImageView?) {
        image?.setBackgroundColor(Color.parseColor("#ff0000"))
    }

    private fun ImageView.offBoxClicked(image: ImageView?) {
        image?.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
    }

    var scPlayer:Int=0
    var scComp:Int=0
    private fun startSuit(mySuit: String): Unit {

        val listElement = arrayOf(paper, rock, scissors)
        val compSuit = listElement.random()
        var result: String = ""
        when (compSuit) {
            "paper" -> kertas_right.onBoxClicked(kertas_right)
            "scissors" -> gunting_right.onBoxClicked(gunting_right)
            "rock" -> batu_right.onBoxClicked(batu_right)
        }
        when (mySuit) {
            "scissors" -> {
                when (compSuit) {
                    "scissors" -> result = "draw"
                    "paper" -> result = "win"
                    "rock" -> result = "lost"
                }
            }
            "paper" -> {
                when (compSuit) {
                    "scissors" -> result = "lost"
                    "paper" -> result = "draw"
                    "rock" -> result = "win"

                }
            }

            "rock" -> {
                when (compSuit) {
                    "scissors" -> result = "win"
                    "paper" -> result = "lost"
                    "rock" -> result = "draw"
                }
            }
        }
        when (result) {
            "win" -> {
                midImage.setImageResource(R.drawable.win)
                scPlayer++
                scorePlayer.text = "Player = " + scPlayer
            }
            "lost" -> {
                midImage.setImageResource(R.drawable.lost)
                scComp++
                scoreComp.text = "Computer = " + scComp
            }
            else -> midImage.setImageResource(R.drawable.draw)
        }
    }

    private fun playAgain() {
        playAgainButton.setOnClickListener {
            batu_left.isEnabled = true
            kertas_left.isEnabled = true
            gunting_left.isEnabled = true
            kertas_left.offBoxClicked(kertas_left)
            gunting_left.offBoxClicked(gunting_left)
            batu_left.offBoxClicked(batu_left)
            kertas_right.offBoxClicked(kertas_right)
            gunting_right.offBoxClicked(gunting_right)
            batu_right.offBoxClicked(batu_right)
            midImage.setImageResource(R.drawable.versus)
        }
    }

    private fun newGame() {
        newGameButton.setOnClickListener {
            batu_left.isEnabled = true
            kertas_left.isEnabled = true
            gunting_left.isEnabled = true
            kertas_left.offBoxClicked(kertas_left)
            gunting_left.offBoxClicked(gunting_left)
            batu_left.offBoxClicked(batu_left)
            kertas_right.offBoxClicked(kertas_right)
            gunting_right.offBoxClicked(gunting_right)
            batu_right.offBoxClicked(batu_right)
            midImage.setImageResource(R.drawable.versus)
            scorePlayer.text = "Player = 0"
            scoreComp.text = "Computer = 0"
            scComp=0
            scPlayer=0

        }

    }
}













