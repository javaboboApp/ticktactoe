package com.example.ticktock.models.game

import com.example.ticktock.models.Board.Board
import com.example.ticktock.models.Player
import com.example.ticktock.models.game.GameEvent.*
import com.example.ticktock.utils.Constants.ERROR_MSG_FINISHED
import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.lang.IllegalStateException

interface IGame {
    fun makeMove(row: Int, colum: Int)
    fun getEventObservable(): Observable<GameEvent>
    fun reset()
    fun getCurrentState(): Status
}

enum class Status {
    RUNNING, FINISHED
}


open class Game(val board: Board, var currentPlayer: Player) :
    IGame {
    private val gameEventObservable = PublishSubject.create<GameEvent>()
    private var status: Status;

    init {
        status = Status.RUNNING

    }

    open override fun makeMove(x: Int, y: Int) {
        if (status == Status.FINISHED) {
            gameEventObservable.onNext(ERROR(ERROR_MSG_FINISHED))
            return
        }
        try {
            val prevPlayer = currentPlayer
            val isFinished = board.move(x, y, currentPlayer)
            currentPlayer = currentPlayer.nextPlayer()
            if (!isFinished)
                gameEventObservable.onNext(MADE_MOVED(x, y, prevPlayer))
            else {
                status = Status.FINISHED
                gameEventObservable.onNext(GAME_FINISHED(board.getCurrentState(), x, y, prevPlayer))
            }
        } catch (ex: IllegalStateException) {
            //TODO: It happen when an illegal move come up, would be a good idea in the new version to create a custom exeption.
            gameEventObservable.onNext(ERROR(ex.message))
        }


    }

    open override fun getEventObservable(): Observable<GameEvent> = gameEventObservable.hide()


    open override fun reset() {
        board.reset()
        gameEventObservable.onNext(RESETED())
        status = Status.RUNNING
    }

    open override fun getCurrentState(): Status = status


}