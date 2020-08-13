package com.example.ticktock.models

import com.example.ticktock.utils.Constants.PLAYER_1
import com.example.ticktock.utils.Constants.PLAYER_2

class Player(val id: Int) {


    fun nextPlayer(): Player {
        return if (this ==  PLAYER_1) {
            PLAYER_2
        } else PLAYER_1

    }

}