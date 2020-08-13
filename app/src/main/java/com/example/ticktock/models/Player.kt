package com.example.ticktock.models

import com.example.ticktock.utils.Constants.PLAYER_1
import com.example.ticktock.utils.Constants.PLAYER_2

class Player(val id: Int, val name : String) {


    fun nextPlayer(): Player {
        return if (this ==  PLAYER_1) {
            PLAYER_2
        } else PLAYER_1

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


}