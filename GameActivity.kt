package com.example.cardgame

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class GameActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var questionView: TextView
    private lateinit var answerView: EditText
    private lateinit var button: Button
    lateinit var coinView: TextView
    var index = 0
    var score = 0
    var deck = mutableListOf<Card>()

    lateinit var card1View: ImageView
    lateinit var card2View: ImageView
    lateinit var card3View: ImageView
    lateinit var opCard1View: ImageView
    lateinit var opCard2View: ImageView
    lateinit var opCard3View: ImageView

    lateinit var card1: Card
    lateinit var card2: Card
    lateinit var card3: Card

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        title = findViewById(R.id.titleTextView)
        answerView = findViewById(R.id.answerView)
        questionView = findViewById(R.id.questionView)
        coinView = findViewById(R.id.coinView)

        card1View = findViewById(R.id.card1)
        card2View = findViewById(R.id.card2)
        card3View = findViewById(R.id.card3)

        opCard1View = findViewById(R.id.opCard1)
        opCard2View = findViewById(R.id.opCard2)
        opCard3View = findViewById(R.id.opCard3)

        createDeck()
        deck.shuffle()

        card1 = dealCard(card1View)
        card2 = dealCard(card2View)
        card3 = dealCard(card3View)

        button = findViewById<Button>(R.id.doneButton)
        button.setOnClickListener {
            handleButtonPress()
        }
    }

    fun createDeck() {

        val card = Card(R.drawable.ace_clubs, "A", "Clubs")
        val card2 = Card(R.drawable.ace_spades, "A", "Spades")
        val card3 = Card(R.drawable.ace_hearts, "A", "Hearts")
        val card4 = Card(R.drawable.ace_diamonds, "A", "Diamonds")

        val card5 = Card(R.drawable.king_clubs, "K", "Clubs")
        val card6 = Card(R.drawable.king_spades, "K", "Spades")
        val card7 = Card(R.drawable.king_hearts, "K", "Hearts")
        val card8 = Card(R.drawable.king_diamonds, "K", "Diamonds")

        val card9 = Card(R.drawable.queen_clubs, "Q", "Clubs")
        val card10 = Card(R.drawable.queen_spades, "Q", "Spades")
        val card11 = Card(R.drawable.queen_hearts, "Q", "Hearts")
        val card12 = Card(R.drawable.queen_diamonds, "Q", "Diamonds")

        deck = arrayListOf(
            card,
            card2,
            card3,
            card4,
            card5,
            card6,
            card7,
            card8,
            card9,
            card10,
            card11,
            card12
        )
    }

    fun handleButtonPress() {

        if (index < 2) {
            var answer = answerView.text.toString()

            if (answer.contains("1")) {
                dealCard(card1View)
            }
            if (answer.contains("2")) {
                dealCard(card2View)
            }
            if (answer.contains("3")) {
                dealCard(card3View)
            }

            index++
            title.text = "Round 2 of 2"

        } else if (button.text != "Play again"){
            val opCard1 = dealCard(opCard1View)
            val opCard2 = dealCard(opCard2View)
            val opCard3 = dealCard(opCard3View)
            //Kolla om det är en stege, par elr triss

            val combo: Int = checkCombo(card1, card2, card3)
            val opCombo: Int = checkCombo(opCard1, opCard2, opCard3)

            title.setTextColor(Color.parseColor("#26703C"))

            if (combo > opCombo) {
                questionView.text = "You won, +10coins"
                score += 10

            } else if (combo < opCombo) {
                questionView.text = "You lost, -5 coins"
                score -= 5
            }else{
                questionView.text = "It's a tie"
            }
            coinView.text = "Coins: $score"
            button.text = "Play again"
        }
    }

    fun dealCard(cardView: ImageView): Card {

        //gör så att samma kombo av value och number inte kan finnas två ggr

        val currentCard = deck[0]
        cardView.setImageResource(currentCard.image)
        deck.remove(currentCard)
        return currentCard
    }

    fun checkCombo(cardA: Card, cardB: Card, cardC: Card): Int {

        if (cardA.value == cardB.value && cardB.value == cardC.value) {
            //Triss
            return 2
        } else if (cardA.value == cardB.value || cardB.value == cardC.value || cardA.value == cardC.value) {
            //Par
            return 1
        }
        return 3
    }
}


