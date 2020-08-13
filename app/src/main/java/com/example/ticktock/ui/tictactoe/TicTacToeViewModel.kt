package com.example.ticktock.ui.tictactoe

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticktock.R
import com.example.ticktock.models.game.Game
import com.example.ticktock.models.game.GameEvent
import com.example.ticktock.models.game.GameEvent.*
import io.reactivex.disposables.CompositeDisposable

private const val TAG = "TicTacToeViewModel"

class TicTacToeViewModel(private var game: Game) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var currentsImg: ObservableArrayMap<String, Int>? = null
    private val _result = MutableLiveData<GameEvent>()
    val result: LiveData<GameEvent> = _result

    init {
        compositeDisposable.add(game.getEventObservable().subscribe(this::handleEvent))
        initCurrentImg()
    }

    fun initCurrentImg() {
        currentsImg = ObservableArrayMap()
        currentsImg!!["who_is_next"] = R.drawable.x_player

    }


    fun handleEvent(event: GameEvent) {
        when (event) {
            is MADE_MOVED -> {
                Log.i(TAG, "handleEvent: MADEMOVED ${event.x} ${event.y}")
                currentsImg?.set(
                    "${event.x}${event.y}",
                    if (event.player.id == 1) R.drawable.x_player else R.drawable.circule_player
                )
                currentsImg?.set(
                    "who_is_next",
                    if (event.player.id == 1) R.drawable.circule_player else R.drawable.x_player
                )
            }
            is RESETED -> {
                Log.i(TAG, "handleEvent: RESETED")
                resetGame()

            }
            is GAME_FINISHED -> {
                currentsImg?.set(
                    "${event.x}${event.y}",
                    if (event.player.id == 1) R.drawable.x_player else R.drawable.circule_player
                )

                Log.i(TAG, "handleEvent: FINISHED ${event.state.name}")

            }
            is ERROR -> {
                Log.i(TAG, "hanldeEvent: ${event.msg}")
            }


        }
        _result.value = event
    }

    private fun resetGame() {
        currentsImg?.clear()

    }

    fun reset() {
        Log.i(TAG, "reset: called")
        game.reset()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageViewResource(imageView: ImageView, resource: Int) {
            imageView.setImageResource(resource)
        }
    }

    fun makeMove(x: Int, y: Int) {
        Log.i(TAG, "makeMove: $x -> $y ")
        game.makeMove(x, y)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        currentsImg?.clear()
    }


}