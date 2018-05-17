package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.TestUtils;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckEndTest {

    private CheckEnd underTest;

    @Before
    public void setUp() {
        underTest = new CheckEnd();
    }

    @Test
    public void shouldMarkGameAsWinAWhenPlayerAWins() {
        //Given
        int scoreOfA = 25;
        int scoreOfB = 15;
        int[] board = {0, 0, 0, 0, 0, 0, scoreOfA, 3, 0, 6, 1, 3, 0, scoreOfB};

        Game game = TestUtils.prepareGameWithGivenBoardArray(board);

        //When
        underTest.run(game, Player.A, 0);

        //Then
        Assert.assertEquals(GameStatus.WIN_A, game.getGameStatus());
    }

    @Test
    public void shouldNotModifyStatusWhenGameIsNotOver() {
        //Given
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.A, 0);

        //Then
        Assert.assertEquals(GameStatus.TURN_A, game.getGameStatus());
    }

    @Test
    public void shouldMarkGameAsWinBWhenPlayerBWins() {
        //Given
        int scoreOfA = 15;
        int scoreOfB = 25;
        int[] board = {0, 0, 0, 0, 0, 0, scoreOfA, 3, 0, 6, 1, 3, 0, scoreOfB};
        Game game = TestUtils.prepareGameWithGivenBoardArray(board);

        //When
        underTest.run(game, Player.A, 0);

        //Then
        Assert.assertEquals(GameStatus.WIN_B, game.getGameStatus());
    }

}