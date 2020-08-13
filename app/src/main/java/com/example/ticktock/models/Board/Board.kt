package com.example.ticktock.models.Board

import android.text.BoringLayout
import com.example.ticktock.models.Player
import com.example.ticktock.utils.Constants.COLUMMS
import com.example.ticktock.utils.Constants.ROWS
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

interface IBoard {
    fun move(x: Int, y: Int, player: Player) : Boolean
}


class Board() : IBoard {
    private val matrix: Array<Array<Cell>> =
        Array(ROWS) { Array(COLUMMS) {  Cell() } }
     enum class Status {
         WIN_PLAYER1,DRAW,LOSS
     }
    override fun move(x: Int, y: Int, player: Player): Boolean {
        require(x in 1..ROWS) {
            throw IllegalArgumentException("x must be between 1 and $ROWS")
        }
        require(y in 1..COLUMMS) {
            throw IllegalArgumentException("x must be between 1 and $ROWS")
        }
        require(matrix[x][y].player_id != 0) {
            throw IllegalStateException("This move is not allowed!!")
        }


        matrix[x][y].player_id = player.id

       return true;

    }


}
