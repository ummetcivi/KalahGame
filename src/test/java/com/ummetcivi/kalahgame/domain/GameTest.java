package com.ummetcivi.kalahgame.domain;

import com.ummetcivi.kalahgame.constant.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class GameTest {

    private Game underTest;

    @Before
    public void setUp() {
        underTest = new Game();
    }

    @Test
    public void shouldJoinTheGameAndSetStatusToTurnAWhenStatusIsWaitingOpponent() {
        underTest.join();

        Assert.assertEquals(GameStatus.TURN_A, underTest.getGameStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotJoinTheGameAndThrowIllegalArgumentExceptionWhenGameStatusIsNotWaitingOpponent() {
        //Given
        ReflectionTestUtils.setField(underTest, "gameStatus", GameStatus.TURN_A);

        //When
        underTest.join();
    }

    @Test
    public void shouldRunPlayFunctionAndSetStatusToReturnValue() {
        underTest.play((board, gameStatus) -> GameStatus.TURN_B);

        Assert.assertEquals(GameStatus.TURN_B, underTest.getGameStatus());
    }

    @Test
    public void shouldPlayEndFunctionAndSetStatusToReturnValue() {
        underTest.markIfEnded((board, gameStatus) -> GameStatus.WIN_A);

        Assert.assertEquals(GameStatus.WIN_A, underTest.getGameStatus());
    }

    @Test
    public void shouldGetPlayersScore() {
        final int expectedScore = 15;

        //PlayerB's score
        underTest.board().addStone(Constants.BOARD_LENGTH - 1, 15);

        Assert.assertEquals(expectedScore, underTest.getScoreOf(Player.B));
    }
}