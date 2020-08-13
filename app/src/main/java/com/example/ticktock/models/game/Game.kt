package com.example.ticktock.models.game

import com.example.ticktock.models.Board.Board
import com.example.ticktock.models.Player
import com.example.ticktock.models.game.GameEvent.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.lang.IllegalStateException

interface IGame {
    fun makeMove(row: Int, colum: Int)
    fun suscribe(): Observable<GameEvent>
    fun reset()
}

enum class Status {
    RUNNING, FINISHED
}


class Game(val board: Board, var currentPlayer: Player) :
    IGame {
    private val gameEventObservable = PublishSubject.create<GameEvent>()
    private  var status: Status;

    init {
        status = Status.RUNNING
    }

    override fun makeMove(x: Int, y: Int) {
        if (status == Status.FINISHED) {
            gameEventObservable.doOnNext { ERROR("The game is finished") }
            return
        }
        try {
            val isFinished = board.move(x, y, currentPlayer)
            currentPlayer = currentPlayer.nextPlayer()
            if (!isFinished)
                gameEventObservable.doOnNext { MADE_MOVED(x, y) }
            else {
                gameEventObservable.doOnNext { GAME_FINISHED(board.getCurrentState()) }
                status = Status.FINISHED

            }
        } catch (ex: IllegalStateException) {
            //TODO: It happen when an illegal move come up, would be a good idea in the new version to create a custom exeption.
            gameEventObservable.doOnNext { ERROR(ex.message) }
        }


    }

    override fun suscribe(): Observable<GameEvent> = gameEventObservable.hide()


    override fun reset() {
        board.reset()
        gameEventObservable.doOnNext { RESETED() }
        status = Status.RUNNING
    }


}