package com.example.ticktock.models.Board

import com.example.ticktock.models.Player
import com.example.ticktock.utils.Constants.COLUMMS
import com.example.ticktock.utils.Constants.PLAYER_1
import com.example.ticktock.utils.Constants.PLAYER_2
import com.example.ticktock.utils.Constants.ROWS
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException

class BoardTest {
    @Test(expected = IllegalArgumentException::class)
    fun test_board_throw_exeption_illegal_x_move() {
        val board = Board()
        board.move(-12, 1, PLAYER_1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_board_throw_exeption_illegal_y_move() {
        val board = Board()
        board.move(1, -21, PLAYER_1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_board_throw_exeption_illegal_y_x_move() {
        val board = Board()
        board.move(ROWS, COLUMMS, PLAYER_1)
    }

    @Test
    fun test_board_does_not_throw_exeptions() {
        val board = Board()
        board.move(0, 0, PLAYER_1)
        board.move(0, 1, PLAYER_2)
        board.move(0, 2, PLAYER_1)

        board.move(1, 0, PLAYER_2)
        board.move(1, 1, PLAYER_1)
        board.move(1, 2, PLAYER_2)

        board.move(2, 0, PLAYER_1)
        board.move(2, 1, PLAYER_2)
        board.move(2, 2, PLAYER_1)
    }

    @Test
    fun test_check_that_there_should_not_be_winner_with_this_scenario() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_1))
        assertEquals(false, board.move(0, 1, PLAYER_2))
        assertEquals(false, board.move(0, 2, PLAYER_1))
        assertEquals(Board.State.PLAYING, board.getCurrentState())

    }

    @Test
    fun test_check_that_player_1_should_be_win_with_this_scenario_horizontal() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_1))
        assertEquals(false, board.move(0, 1, PLAYER_1))
        assertEquals(true, board.move(0, 2, PLAYER_1))
        assertEquals(Board.State.WIN_PLAYER1, board.getCurrentState())
    }

    @Test
    fun test_check_that_player_1_should_be_win_with_this_scenario_diagonal() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_1))
        assertEquals(false, board.move(1, 1, PLAYER_1))
        assertEquals(true, board.move(2, 2, PLAYER_1))
        assertEquals(Board.State.WIN_PLAYER1, board.getCurrentState())

    }


    @Test
    fun test_check_that_player_1_should_be_win_with_this_scenario_vertical() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_1))
        assertEquals(false, board.move(1, 0, PLAYER_1))
        assertEquals(true, board.move(2, 0, PLAYER_1))
        assertEquals(Board.State.WIN_PLAYER1, board.getCurrentState())

    }


    @Test
    fun test_check_that_player_2_should_be_win_with_this_scenario_horizontal() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_2))
        assertEquals(false, board.move(0, 1, PLAYER_2))
        assertEquals(true, board.move(0, 2, PLAYER_2))
        assertEquals(Board.State.WIN_PLAYER2, board.getCurrentState())



    }

    @Test
    fun test_check_that_player_2_should_be_win_with_this_scenario_diagonal() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_2))
        assertEquals(false, board.move(1, 1, PLAYER_2))
        assertEquals(true, board.move(2, 2, PLAYER_2))
        assertEquals(Board.State.WIN_PLAYER2, board.getCurrentState())

    }


    @Test
    fun test_check_that_player_2_should_be_win_with_this_scenario_vertical() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_2))
        assertEquals(false, board.move(1, 0, PLAYER_2))
        assertEquals(true, board.move(2, 0, PLAYER_2))
        assertEquals(Board.State.WIN_PLAYER2, board.getCurrentState())

    }


    @Test
    fun test_check_that_should_be_a_winner_with_this_random_scenario_vertical() {
        val board = Board()
        assertEquals(false, board.move(0, 0, PLAYER_2))
        assertEquals(false, board.move(0, 1, PLAYER_1))
        assertEquals(false, board.move(0, 2, PLAYER_2))

        assertEquals(false, board.move(1, 2, PLAYER_1))

        assertEquals(false, board.move(1, 0, PLAYER_2))
        assertEquals(false, board.move(1, 1, PLAYER_1))

        assertEquals(true, board.move(2, 0, PLAYER_2))

        assertEquals(Board.State.WIN_PLAYER2, board.getCurrentState())
    }


}