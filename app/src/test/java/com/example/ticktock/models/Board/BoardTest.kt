package com.example.ticktock.models.Board

import com.example.ticktock.models.Player
import com.example.ticktock.utils.Constants.COLUMMS
import com.example.ticktock.utils.Constants.PLAYER_1
import com.example.ticktock.utils.Constants.ROWS
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException

class BoardTest{
    @Test(expected = IllegalArgumentException::class)
    fun test_board_throw_exeption_illegal_x_move() {
       val board = Board()
        board.move(-12,1, PLAYER_1)
    }
    @Test(expected = IllegalArgumentException::class)
    fun test_board_throw_exeption_illegal_y_move() {
        val board = Board()
        board.move(1,-21, PLAYER_1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_board_throw_exeption_illegal_y_x_move() {
        val board = Board()
        board.move(ROWS, COLUMMS, PLAYER_1)
    }

    @Test
    fun test_board_does_not_throw_exeptions() {
        val board = Board()
        board.move(0, 1, PLAYER_1)
        board.move(0, 2, PLAYER_1)
        board.move(0, 3, PLAYER_1)

        board.move(1, 0, PLAYER_1)
        board.move(1, 1, PLAYER_1)
        board.move(1, 2, PLAYER_1)

        board.move(2, 0, PLAYER_1)
        board.move(2, 1, PLAYER_1)
        board.move(2, 2, PLAYER_1)
    }
}