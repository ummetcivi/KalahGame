package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.TestUtils;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import org.junit.Before;
import org.junit.Test;

public class CheckPitIsNotEmptyTest {

    private CheckPitIsNotEmpty underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new CheckPitIsNotEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPitIsEmpty() {
        //Given
        int[] board = {0, 5, 1, 5, 3, 2, 6, 4, 3, 5, 2, 1, 4, 2};
        Game game = TestUtils.prepareGameWithGivenBoardArray(board);

        //When
        underTest.run(game, Player.A, 0);
    }

    @Test
    public void shouldNotThrowExceptionWhenPitIsNotEmpty() {
        //Given
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.A, 0);
    }
}