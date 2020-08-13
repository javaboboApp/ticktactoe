package com.example.ticktock.models.game

import com.example.ticktock.models.Board.Board

sealed class GameEvent {
    class RESETED() : GameEvent()
    class GAME_FINISHED(val state: Board.State, x: Int, y:Int) : GameEvent(){
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as GAME_FINISHED

            if (state.name != other.state.name) return false

            return true
        }

        override fun hashCode(): Int {
            return state.hashCode()
        }
    }
    data class ERROR(val msg: String?) : GameEvent()
    data class MADE_MOVED(val x: Int, val y: Int) : GameEvent()

}