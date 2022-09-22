package com.example.cardgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class GameActivity : AppCompatActivity() {

    lateinit var answerView : EditText
    lateinit var title : TextView
    var index = 0

    private lateinit var card1Val : ImageView
    private lateinit var card2Val : ImageView
    private lateinit var card3Val : ImageView
    private lateinit var card1Num : TextView
    private lateinit var card2Num : TextView
    private lateinit var card3Num : TextView


    lateinit var opCard1 : ImageView
    lateinit var opCard2 : ImageView
    lateinit var opCard3 : ImageView
    private lateinit var opCard1Val : ImageView
    private lateinit var opCard2Val : ImageView
    private lateinit var opCard3Val : ImageView
    private lateinit var opCard1Num : TextView
    lateinit var opCard2Num : TextView
    lateinit var opCard3Num : TextView




    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        answerView = findViewById(R.id.answerView)

        card1Val = findViewById(R.id.card1Val)
        card2Val = findViewById(R.id.card2Val)
        card3Val = findViewById(R.id.card3Val)
        card1Num = findViewById(R.id.card1Num)
        card2Num = findViewById(R.id.card2Num)
        card3Num = findViewById(R.id.card3Num)


        opCard1 = findViewById(R.id.opCard1)
        opCard2 = findViewById(R.id.opCard2)
        opCard3 = findViewById(R.id.opCard3)
        opCard1Val = findViewById(R.id.opCard1Val)
        opCard2Val = findViewById(R.id.opCard2Val)
        opCard3Val = findViewById(R.id.opCard3Val)
        opCard1Num = findViewById(R.id.opCard1Num)
        opCard2Num = findViewById(R.id.opCard2Num)
        opCard3Num = findViewById(R.id.opCard3Num)


        title = findViewById(R.id.titleTextView)

        dealCard(card1Val, card1Num)
        dealCard(card2Val, card2Num)
        dealCard(card3Val, card3Num)

        val button = findViewById<Button>(R.id.doneButton)
        button.setOnClickListener {
            handleButtonPress()
        }



    }


    fun handleButtonPress() {


        if(index < 2) {
            var answer = answerView.text.toString()

            if (answer.contains("1")) {
                dealCard(card1Val, card1Num)
            }
            if (answer.contains("2")) {
                dealCard(card2Val, card2Num)
            }
            if (answer.contains("3")) {
                dealCard(card3Val, card3Num)
            }

            index++
            title.text = "Round 2 of 2"
        }
        else{
           title.text = "Klar"
            opCard1.setImageResource(R.drawable.blankcard)
            opCard2.setImageResource(R.drawable.blankcard)
            opCard3.setImageResource(R.drawable.blankcard)
            dealCard(opCard1Val, opCard1Num)
            dealCard(opCard2Val, opCard2Num)
            dealCard(opCard3Val, opCard3Num)
            //Kolla om det är en stege, par elr triss
        }

    }

    fun dealCard(value: ImageView, number : TextView) {


        val index = (1..4).random()

        when (index){

            1 -> {
                value.setImageResource(R.drawable.image_hearts)
            }
            2 -> {
                value.setImageResource(R.drawable.image_diamonds)
            }
            3 -> {
                value.setImageResource(R.drawable.image_clubs)
            }
            4 -> {
                value.setImageResource(R.drawable.image_spades)
            }

        }

        val index2 = (1..3).random()

        when (index2){

            1 -> {
                number.text == "A"
            }
            2 -> {
                number.text == "K"
            }
            3 -> {
                number.text == "Q"
            }

        }
        if(index <= 2){
            number.setTextColor(Color.parseColor("#bdbdbd"))

        }else{
            number.setTextColor(Color.parseColor("#bdbdbd"))
        }


        }
    }


