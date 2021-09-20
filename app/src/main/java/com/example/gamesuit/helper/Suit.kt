package com.example.gamesuit.helper

open class Suit(var name: String = "") {
    open fun loseTo(): String {
        return ""
    }

    open fun winFrom(): String {
        return ""
    }

    open fun drawFrom(): String {
        return ""
    }

    open fun actionAgainst(suit: Suit): ResultSuit {
        return when(suit.name) {
            winFrom() -> ResultSuit(ResultSuit.WIN)
            loseTo() -> ResultSuit(ResultSuit.LOSE)
            else -> ResultSuit(ResultSuit.DRAW)
        }
    }
}