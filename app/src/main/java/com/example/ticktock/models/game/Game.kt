package com.example.ticktock.models.game

import com.example.ticktock.models.Board.Board
import com.example.ticktock.models.Player
import com.example.ticktock.models.game.GameEvent.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.lang.IllegalStateException

interface IGame {
    fun makeMove(row: Int, colum: Int)
    fun suscribe() : Observable<GameEvent>
    fun reset()
}

enum class Status {
    RUNNING, FINISHED
}


class Game(val board: Board, var currentPlayer: Player) :
    IGame {
    private val gameEventObservable = PublishSubject.create<GameEvent>()
    private lateinit var status: Status;

    init {
        status = Status.RUNNING
    }

    override fun makeMove(x: Int, y: Int) {
        if (status == Status.FINISHED) {
            gameEventObservable.doOnNext { GAME_FINISHED() }
            return
        }
        try {
            board.move(x, y, currentPlayer)
            currentPlayer = currentPlayer.nextPlayer()
            gameEventObservable.doOnNext { MADE_MOVED(x,y) }
        } catch (ex: IllegalStateException) {
            //something was wrong
        }


    }

    override fun suscribe(): Observable<GameEvent> = gameEventObservable.hide()



    override fun reset() {
        gameEventObservable.doOnNext { RESETED() }
        status = Status.RUNNING
    }


}