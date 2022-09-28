package com.example.cardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<ImageButton>(R.id.playButton)
        button.setOnClickListener {
            handleButtonPress()
        }
    }
    fun handleButtonPress(){
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}