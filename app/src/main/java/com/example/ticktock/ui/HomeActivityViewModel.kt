package com.example.ticktock.ui

import androidx.lifecycle.ViewModel
import com.example.ticktock.models.game.Game

class HomeActivityViewModel(private var game: Game) : ViewModel() {


    fun madeMove() {
        game.makeMove(1, 2)
    }

}