package com.example.ticktock.models.game

import com.example.ticktock.models.Board.Board
import com.example.ticktock.utils.Constants.MOVE_NOT_ALLOWED_MSG
import com.example.ticktock.utils.Constants.PLAYER_1
import com.example.ticktock.utils.Constants.PLAYER_2
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GameTest {
    @Mock
    private lateinit var fakeGame: Game

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun test_check_that_game_is_running_when_it_is_created() {
        val board = Board()
        val game = Game(board, PLAYER_1)
        Assert.assertEquals(game.getCurrentState(), Status.RUNNING)
    }

    @Test
    fun test_check_that_made_move_event_has_been_emited() {
        val board = Board()
        val game = Game(board, PLAYER_1)
        val gameEventObservable = game.getEventObservable()

        val testSubscriber: TestObserver<GameEvent> = TestObserver()
        gameEventObservable.subscribe(testSubscriber)
        game.makeMove(1, 2)

        testSubscriber
            .assertSubscribed()
            .assertNoErrors()
            .assertValue(GameEvent.MADE_MOVED(1, 2,PLAYER_1))


    }

    @Test
    fun test_finish_state_is_emited_by_the_game_class_in_this_scenario() {
        val board = Board()
        val game = Game(board, PLAYER_1)
        val gameEventObservable = game.getEventObservable()

        val testSubscriber: TestObserver<GameEvent> = TestObserver()
        gameEventObservable.subscribe(testSubscriber)

        game.makeMove(0, 0)

        game.currentPlayer = PLAYER_1

        testSubscriber
            .assertSubscribed()
            .assertNoErrors()
            .assertValue(GameEvent.MADE_MOVED(0, 0,PLAYER_1))
        game.currentPlayer = PLAYER_1

        game.makeMove(0, 1)


        testSubscriber.assertValues(GameEvent.MADE_MOVED(0, 0, PLAYER_1), GameEvent.MADE_MOVED(0, 1,PLAYER_1))
        game.currentPlayer = PLAYER_1

        game.makeMove(0, 2)

        assertEquals(Status.FINISHED, game.getCurrentState())

        testSubscriber.assertValues(
            GameEvent.MADE_MOVED(0, 0,PLAYER_1), GameEvent.MADE_MOVED(0, 1,PLAYER_1),
            GameEvent.GAME_FINISHED(board.getCurrentState(), 0, 2, PLAYER_2)
        )

    }

    @Test
    fun test_check_that_draw_in_this_scenario() {
        val board = Board()
        val game = Game(board, PLAYER_1)
        val gameEventObservable = game.getEventObservable()

        val testSubscriber: TestObserver<GameEvent> = TestObserver()
        gameEventObservable.subscribe(testSubscriber)

        game.makeMove(0, 0)
        game.makeMove(0, 1)
        game.makeMove(0, 2)

        game.makeMove(1, 0)
        game.makeMove(1, 1)
        game.makeMove(1, 2)

        game.currentPlayer = PLAYER_2

        game.makeMove(2, 0)
        game.makeMove(2, 1)
        game.currentPlayer = PLAYER_2
        game.makeMove(2, 2)


        testSubscriber
            .assertSubscribed()
            .assertNoErrors()
            .assertValues(
                GameEvent.MADE_MOVED(0, 0, PLAYER_1),
                GameEvent.MADE_MOVED(0, 1, PLAYER_2),
                GameEvent.MADE_MOVED(0, 2, PLAYER_1),

                GameEvent.MADE_MOVED(1, 0, PLAYER_2),
                GameEvent.MADE_MOVED(1, 1, PLAYER_1),
                GameEvent.MADE_MOVED(1, 2, PLAYER_2),

                GameEvent.MADE_MOVED(2, 0, PLAYER_2),
                GameEvent.MADE_MOVED(2, 1, PLAYER_1),
                GameEvent.GAME_FINISHED(board.getCurrentState(), 2, 2, PLAYER_2)
            )


        assertEquals(Status.FINISHED, game.getCurrentState())

    }

    @Test
    fun test_check_an_error_is_emited_when_we_called_make_move_with_wrong_arguments() {
        val board = Board()
        val game = Game(board, PLAYER_1)
        val gameEventObservable = game.getEventObservable()

        val testSubscriber: TestObserver<GameEvent> = TestObserver()
        gameEventObservable.subscribe(testSubscriber)

        game.makeMove(0, 0)
        game.makeMove(0, 0)

        testSubscriber.assertValues(
            GameEvent.MADE_MOVED(0, 0, PLAYER_1),
            GameEvent.ERROR(MOVE_NOT_ALLOWED_MSG)
        )
    }
}