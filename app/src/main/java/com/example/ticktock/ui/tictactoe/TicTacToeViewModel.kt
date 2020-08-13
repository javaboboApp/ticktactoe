package com.example.ticktock.ui.tictactoe

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticktock.models.game.Game
import com.example.ticktock.models.game.GameEvent
import com.example.ticktock.models.game.GameEvent.*
import io.reactivex.disposables.CompositeDisposable

private const val TAG = "TicTacToeViewModel"
class TicTacToeViewModel(private var game: Game) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(game.getEventObservable().subscribe(this::handleEvent))
    }

    fun handleEvent(event: GameEvent) {
        when (event) {
            is MADE_MOVED -> {
                Log.i(TAG, "handleEvent: MADEMOVED ${event.x} ${event.y}")
            }
            is RESETED -> {
                Log.i(TAG, "handleEvent: RESETED")

            }
            is GAME_FINISHED -> {
                Log.i(TAG, "handleEvent: FINISHED ${event.state.name}")

            }
            is ERROR -> {
               Log.i(TAG, "hanldeEvent: ${event.msg}")
            }

        }
    }

    fun makeMove(x: Int, y: Int){
        Log.i(TAG, "makeMove: $x -> $y ")
        game.makeMove(x, y)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }



}