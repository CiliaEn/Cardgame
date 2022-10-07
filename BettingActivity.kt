package com.example.cardgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class BettingActivity : AppCompatActivity() {

    lateinit var button5: Button
    lateinit var button10: Button
    lateinit var button15: Button
    lateinit var quitButton: Button
    lateinit var card1Button: ImageButton
    lateinit var card2Button: ImageButton
    lateinit var card3Button: ImageButton
    private lateinit var coinView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_betting)

        coinView = findViewById(R.id.coinView)
        var coins = intent.getIntExtra("coins", 0)
        coinView.text = "Coins: $coins"

        button5 = findViewById(R.id.button5)
        button5.setOnClickListener {
            handleButtonPress(5)
        }
        button10 = findViewById(R.id.button10)
        button10.setOnClickListener {
            if (coins < 10) {
                Toast.makeText(this, "You don't have 10 coins to bet", Toast.LENGTH_SHORT).show()
            } else {
                handleButtonPress(10)
            }
        }
        button15 = findViewById(R.id.button15)
        button15.setOnClickListener {
            if (coins < 15) {
                Toast.makeText(this, "You don't have 15 coins to bet", Toast.LENGTH_SHORT).show()
            } else {
                handleButtonPress(15)
            }
        }

        var currentCard1 = intent.getSerializableExtra("currentCard1") as Card
        card1Button = findViewById(R.id.card1Button)
        card1Button.setImageResource(currentCard1.image)

        var currentCard2 = intent.getSerializableExtra("currentCard2") as Card
        card2Button = findViewById(R.id.card2Button)
        card2Button.setImageResource(currentCard2.image)

        var currentCard3 = intent.getSerializableExtra("currentCard3") as Card
        card3Button = findViewById(R.id.card3Button)
        card3Button.setImageResource(currentCard3.image)

        quitButton = findViewById(R.id.quitButton)
        quitButton.setOnClickListener {
            handleButtonPress(0)
        }
    }

    private fun handleButtonPress(bet: Int) {

        val intent = Intent()
        intent.putExtra("bet", bet)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}