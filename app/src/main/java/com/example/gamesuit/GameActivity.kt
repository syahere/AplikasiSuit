package com.example.gamesuit

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesuit.element.Paper
import com.example.gamesuit.element.Scissors
import com.example.gamesuit.element.Rock
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
    private val playAgainButton: ImageView by lazy {
        findViewById(R.id.play_again)
    }
    private val midImage: ImageView by lazy {
        findViewById(R.id.result)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        kertas_left.setOnClickListener {
            kertas_left.onBoxClicked(kertas_left)
            batu_left.isEnabled = false
            gunting_left.isEnabled = false
            val mySuit = "paper"
            startSuitWithCom()
            resultGame(mySuit)
            playAgain()

        }
        batu_left.setOnClickListener {
            batu_left.onBoxClicked(batu_left)
            kertas_left.isEnabled = false
            gunting_left.isEnabled = false
            val mySuit = "rock"
            startSuitWithCom()
            resultGame(mySuit)
            playAgain()

        }
        gunting_left.setOnClickListener {
            gunting_left.onBoxClicked(gunting_left)
            batu_left.isEnabled = false
            kertas_left.isEnabled = false
            val mySuit = "scissors"
            startSuitWithCom()
            resultGame(mySuit)
            playAgain()

        }
    }

    private fun ImageView.onBoxClicked(image: ImageView?) {
        image?.setBackgroundColor(Color.parseColor("#ff0000"))
    }

    private fun ImageView.offBoxClicked(image: ImageView?) {
        image?.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
    }

    private fun startSuitWithCom() {
        val paper = Paper("paper")
        val rock = Rock("rock")
        val scissors = Scissors("scissors")
    }

    private fun resultGame(mySuit: String): Unit {

        val listElement = arrayOf(paper, rock, scissors)
        val compSuit = listElement.random()
        when (compSuit) {
            "paper" -> kertas_right.onBoxClicked(kertas_right)
            "scissors" -> gunting_right.onBoxClicked(gunting_right)
            "rock" -> batu_right.onBoxClicked(batu_right)
        }
        when (mySuit) {
            "scissors" -> {
                when (compSuit) {
                    "scissors" -> midImage.setImageResource(R.drawable.draw)
                    "paper" -> midImage.setImageResource(R.drawable.win)
                    "rock" -> midImage.setImageResource(R.drawable.lost)
                }
            }
            "paper" -> {
                when (compSuit) {
                    "scissors" -> midImage.setImageResource(R.drawable.lost)
                    "paper" -> midImage.setImageResource(R.drawable.draw)
                    "rock" -> midImage.setImageResource(R.drawable.win)
                }
            }

            "rock" -> {
                when (compSuit) {
                    "scissors" -> midImage.setImageResource(R.drawable.win)
                    "paper" -> midImage.setImageResource(R.drawable.lost)
                    "rock" -> midImage.setImageResource(R.drawable.draw)
                }
            }
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
            startSuitWithCom()
        }
    }
}













