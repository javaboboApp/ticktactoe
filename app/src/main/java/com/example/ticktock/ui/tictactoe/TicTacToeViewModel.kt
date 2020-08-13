package com.example.ticktock.ui.tictactoe

import androidx.lifecycle.ViewModel
import com.example.ticktock.models.game.Game
import com.example.ticktock.models.game.GameEvent
import com.example.ticktock.models.game.GameEvent.*
import io.reactivex.disposables.CompositeDisposable

class TicTacToeViewModel(private var game: Game) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(game.getEventObservable().subscribe(this::handleEvent))
    }

    fun handleEvent(event: GameEvent) {
        when (event) {
            is MADE_MOVED -> {

            }
            is RESETED -> {

            }
            is GAME_FINISHED -> {

            }
            is ERROR -> {

            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }



}