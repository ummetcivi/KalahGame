package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.TestUtils;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import org.junit.Before;
import org.junit.Test;

public class CheckTurnTest {

    private CheckTurn underTest;

    @Before
    public void setUp() {
        underTest = new CheckTurn();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenItIsNotPlayersTurn() {
        //Given
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.B, 0);
    }

    @Test
    public void shouldNotThrowExceptionWhenItIsPlayersTurn() {
        //Given
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.A, 0);
    }
}