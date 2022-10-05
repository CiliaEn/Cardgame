package com.example.cardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<ImageButton>(R.id.playButton)
        playButton.setOnClickListener {
            handleButtonPress()
        }
    }
    private fun handleButtonPress(){
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}