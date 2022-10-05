package com.example.cardgame

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

class GameActivity : AppCompatActivity() {

    private lateinit var titleView: TextView
    private lateinit var questionView: TextView
    private lateinit var doneButton: Button
    lateinit var quitButton: Button
    lateinit var coinView: TextView
    var roundCounter = 0
    var coins = 15
    var deck = mutableListOf<Card>()
    var bet : Int = 0
    var getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        //3
        bet = result.data?.getIntExtra("bet", 0) ?: 0
        Log.d("!!!", "back $bet")
    }

    lateinit var card1Button: ImageButton
    lateinit var card2Button: ImageButton
    lateinit var card3Button: ImageButton
    lateinit var opCard1View: ImageView
    lateinit var opCard2View: ImageView
    lateinit var opCard3View: ImageView

    lateinit var currentCard1: Card
    lateinit var currentCard2: Card
    lateinit var currentCard3: Card



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        card1Button = findViewById(R.id.card1Button)
        card2Button = findViewById(R.id.card2Button)
        card3Button = findViewById(R.id.card3Button)

        opCard1View = findViewById(R.id.opCard1)
        opCard2View = findViewById(R.id.opCard2)
        opCard3View = findViewById(R.id.opCard3)

        questionView = findViewById(R.id.questionView)
        coinView = findViewById(R.id.coinView)

        doneButton = findViewById(R.id.doneButton)
        doneButton.setOnClickListener {
            handleButtonPress()
        }
        quitButton = findViewById(R.id.quitButton)
        quitButton.setOnClickListener {
            finish()
        }
        card1Button.setOnClickListener{
            currentCard1.setCardStatusToTrue()
        }
        card2Button.setOnClickListener{
            currentCard2.setCardStatusToTrue()
        }
        card3Button.setOnClickListener{
            currentCard3.setCardStatusToTrue()
        }

        newRound()
    }

    private fun newRound() {
        titleView = findViewById(R.id.titleTextView)
        titleView.text = "Round 1 of 2"
        titleView.setTextColor(Color.parseColor("#000000"))
        questionView.text = "Which cards do you want to discard?"
        doneButton.text = "Done"
        opCard1View.setImageResource(R.drawable.image_backofcard)
        opCard2View.setImageResource(R.drawable.image_backofcard)
        opCard3View.setImageResource(R.drawable.image_backofcard)

        createDeck()
        deck.shuffle()

        currentCard1 = dealCard(card1Button)
        currentCard2 = dealCard(card2Button)
        currentCard3 = dealCard(card3Button)

        roundCounter = 0
    }


    private fun createDeck() {

        val card1 = Card(R.drawable.a_clubs, "A", "Clubs")
        val card2 = Card(R.drawable.a_spades, "A", "Spades")
        val card3 = Card(R.drawable.a_hearts, "A", "Hearts")
        val card4 = Card(R.drawable.a_diamonds, "A", "Diamonds")

        val card5 = Card(R.drawable.k_clubs, "K", "Clubs")
        val card6 = Card(R.drawable.k_spades, "K", "Spades")
        val card7 = Card(R.drawable.k_hearts, "K", "Hearts")
        val card8 = Card(R.drawable.k_diamonds, "K", "Diamonds")

        val card9 = Card(R.drawable.q_clubs, "Q", "Clubs")
        val card10 = Card(R.drawable.q_spades, "Q", "Spades")
        val card11 = Card(R.drawable.q_hearts, "Q", "Hearts")
        val card12 = Card(R.drawable.q_diamonds, "Q", "Diamonds")

        deck = arrayListOf(
            card1,
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

    private fun handleButtonPress() {

        if (doneButton.text == "Play again") {
            newRound()
        } else if (roundCounter < 2) {

            if (currentCard1.getCardStatus()){
                currentCard1 = dealCard(card1Button)
                currentCard1.setCardStatusToFalse()
            }
            if (currentCard2.getCardStatus()){
                currentCard2= dealCard(card2Button)
                currentCard2.setCardStatusToFalse()
            }
            if (currentCard3.getCardStatus()){
                currentCard3 = dealCard(card3Button)
                currentCard3.setCardStatusToFalse()
            }
            roundCounter++
            titleView.text = "Round 2 of 2"

        } else{
            val intent = Intent(this, BettingActivity::class.java)

            intent.putExtra("coins", coins)
            intent.putExtra("currentCard1", currentCard1)
            intent.putExtra("currentCard2", currentCard2)
            intent.putExtra("currentCard3", currentCard3)

            getContent.launch(intent)


            val opCard1 = dealCard(opCard1View)
            val opCard2 = dealCard(opCard2View)
            val opCard3 = dealCard(opCard3View)

            val combo: Int = getCombo(currentCard1, currentCard2, currentCard3)
            val opCombo: Int = getCombo(opCard1, opCard2, opCard3)

            if (combo > opCombo) {

                questionView.text = "You won, here's ${bet * 2} coins"
                coins += bet * 2
                Log.d("!!!", "Win $bet")
            } else if (combo < opCombo) {
                Log.d("!!!", "lost $bet")
                questionView.text = "You lost"
                coins -= bet
            } else{
                questionView.text = "It's a tie"
            }

            if (coins <= 0){
                titleView.text = "You ran out of coins"
                doneButton.text = "Start new round"
                coinView.text = "Coins: 15"
            }
            //1
            Log.d("!!!", "round over $bet")
                coinView.text = "Coins: $coins"
                doneButton.text = "Play again"

        }
    }

    private fun dealCard(cardView: ImageView): Card {

        val card = deck[0]
        cardView.setImageResource(card.image)
        deck.remove(card)
        return card
    }

    private fun getCombo(cardA: Card, cardB: Card, cardC: Card): Int {

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


