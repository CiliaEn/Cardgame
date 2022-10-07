package com.example.cardgame

import java.io.Serializable

class Card(var image: Int, var value: String) : Serializable {

    var changeCard: Boolean = false

    fun getCardStatus(): Boolean {
        return changeCard
    }

    fun setCardStatusToTrue() {
        changeCard = true
    }

    fun setCardStatusToFalse() {
        changeCard = false
    }
}