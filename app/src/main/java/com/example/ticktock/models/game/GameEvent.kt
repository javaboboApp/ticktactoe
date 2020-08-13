package com.example.ticktock.models.game

import com.example.ticktock.models.Board.Board

sealed class GameEvent {
    class RESETED() : GameEvent()
    class GAME_FINISHED(val state: Board.State) : GameEvent()
    data class ERROR(val msg: String?) : GameEvent()
    data class MADE_MOVED(val x: Int, val y: Int) : GameEvent()
}