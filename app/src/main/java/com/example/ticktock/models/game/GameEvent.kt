package com.example.ticktock.models.game

sealed class GameEvent {
    class RESETED() : GameEvent()
    class GAME_FINISHED() : GameEvent()
    data class ERROR(val msg: String) : GameEvent()
    data class MADE_MOVED(val x: Int, val y: Int) : GameEvent()
}