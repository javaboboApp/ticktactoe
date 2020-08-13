package com.example.ticktock.models

sealed class Player(val id: Int) {
    class PLAYER_1() : Player(1)
    class PLAYER_2() : Player(-1)

    fun nextPlayer(): Player {
        return if (this == PLAYER_1::class) {
            PLAYER_2()
        } else PLAYER_1()

    }
}