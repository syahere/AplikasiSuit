package com.example.gamesuit

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesuit.element.Paper
import com.example.gamesuit.element.Scissors
import com.example.gamesuit.element.Rock
import com.example.gamesuit.element.StringContainer
import com.example.gamesuit.element.StringContainer.paper
import com.example.gamesuit.element.StringContainer.rock
import com.example.gamesuit.element.StringContainer.scissors
import com.example.gamesuit.helper.Suit
import com.example.gamesuit.helper.ResultSuit

class GameActivity : AppCompatActivity() {

    private lateinit var kertas_right: ImageView
    private lateinit var batu_right: ImageView
    private lateinit var gunting_right: ImageView
    private lateinit var kertas_left: ImageView
    private lateinit var batu_left: ImageView
    private lateinit var gunting_left: ImageView
    private lateinit var playAgainButton: ImageView
    private lateinit var midImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        kertas_right = findViewById(R.id.kertas_right)
        batu_right = findViewById(R.id.batu_right)
        gunting_right = findViewById(R.id.gunting_right)
        kertas_left = findViewById(R.id.kertas_left)
        batu_left = findViewById(R.id.batu_left)
        gunting_left = findViewById(R.id.gunting_left)
        playAgainButton = findViewById(R.id.play_again)


        kertas_left.setOnClickListener {
            kertas_left.onBoxClicked(kertas_left)
            batu_left.isEnabled = false
            gunting_left.isEnabled = false
            startSuitWithCom()

        }
        batu_left.setOnClickListener {
            batu_left.onBoxClicked(batu_left)
            kertas_left.isEnabled = false
            gunting_left.isEnabled = false
            startSuitWithCom()

        }
        gunting_left.setOnClickListener {
            gunting_left.onBoxClicked(gunting_left)
            batu_left.isEnabled = false
            kertas_left.isEnabled = false
            startSuitWithCom()

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
        val resultList: List<Suit> = listOf(paper, rock, scissors)
        val compSuit = resultList.random()


        when (compSuit.name) {
            "paper" -> kertas_right.onBoxClicked(kertas_right)
            "scissors" -> gunting_right.onBoxClicked(gunting_right)
            else -> batu_right.onBoxClicked(batu_right)
        }
        val mySuit = when {
            kertas_left.hasOnClickListeners() -> Paper(StringContainer.paper)
            gunting_left.hasOnClickListeners() -> Scissors(StringContainer.scissors)
            else -> Rock(StringContainer.rock)
        }

        val result = mySuit.actionAgainst(compSuit)

        if (result.status == "WIN") {
            midImage.setImageResource(R.drawable.win)
        }
        if (result.status == "LOSE") {
            midImage.setImageResource(R.drawable.lost)
        } else {
            midImage.setImageResource(R.drawable.draw)
        }
        playAgainButton.setOnClickListener {
            batu_left.isEnabled = true
            kertas_left.isEnabled = true
            gunting_left.isEnabled = true
            kertas_right.offBoxClicked(kertas_right)
            gunting_right.offBoxClicked(gunting_right)
            batu_right.offBoxClicked(batu_right)
            startSuitWithCom()

        }

    }
}










