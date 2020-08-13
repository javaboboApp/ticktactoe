package com.example.ticktock.models.Board

import android.text.BoringLayout
import android.util.Log
import com.example.ticktock.models.Player
import com.example.ticktock.utils.Constants.COLUMMS
import com.example.ticktock.utils.Constants.MAX_MOVES_ALLOWED
import com.example.ticktock.utils.Constants.MOVE_NOT_ALLOWED_MSG
import com.example.ticktock.utils.Constants.PLAYER_1
import com.example.ticktock.utils.Constants.ROWS
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

interface IBoard {
    fun move(x: Int, y: Int, player: Player): Boolean
    fun getCurrentState(): Board.State
    fun reset()
}

private const val TAG = "Board"

class Board() : IBoard {
    private var numMoves = 0
    private lateinit var state: State
    private var matrix: Array<Array<Cell>> =
        Array(ROWS) { Array(COLUMMS) { Cell() } }

    enum class State {
        PLAYING, WIN_PLAYER1, WIN_PLAYER2, DRAW
    }

    init {
        state = State.PLAYING
    }

    /*
    * @Param x position x of the board
    * @Param y position y of the board
    * @Param player who want to play
    * @Return true if the game is over otherwise return false
    * */
    override fun move(x: Int, y: Int, player: Player): Boolean {
        require(x in 0..ROWS - 1) {
            throw IllegalArgumentException("x must be between 0 and ${ROWS - 1}")
        }
        require(y in 0..COLUMMS - 1) {
            throw IllegalArgumentException("x must be between 0 and ${ROWS - 1}")
        }
        require(matrix[x][y].player_id == 0) {
            throw IllegalStateException(MOVE_NOT_ALLOWED_MSG)
        }

        matrix[x][y].player_id = player.id
        numMoves++

        val checkStatus = checkGameStatus()

        if (!checkStatus) {
            if (numMoves == MAX_MOVES_ALLOWED) {
                state = State.DRAW
                return true
            }
        } else {

            state = if (player == PLAYER_1) State.WIN_PLAYER1 else State.WIN_PLAYER2
        }


        return checkStatus

    }

    override fun getCurrentState(): State = state

    override fun reset() {
        numMoves = 0
        matrix = Array(ROWS) { Array(COLUMMS) { Cell() } }
        state = State.PLAYING
    }

    private fun checkGameStatus(): Boolean {
        //TODO WE KNOWS THAT IS A 3X3 BOARD
        return (matrix[0][0] == matrix[0][1] && matrix[0][0] == matrix[0][2]) ||
                (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]) ||
                (matrix[0][0] == matrix[1][0] && matrix[0][0] == matrix[2][0]) ||
                (matrix[2][0] == matrix[2][1] && matrix[2][0] == matrix[2][2]) ||
                (matrix[2][0] == matrix[1][1] && matrix[0][0] == matrix[0][2]) ||

                (matrix[0][2] == matrix[1][2] && matrix[0][2] == matrix[2][2]) ||
                (matrix[0][1] == matrix[1][1] && matrix[0][1] == matrix[2][1]) ||
                (matrix[1][0] == matrix[1][1] && matrix[1][0] == matrix[1][2])
    }


}
