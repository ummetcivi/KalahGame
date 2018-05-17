package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.TestUtils;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import org.junit.Before;
import org.junit.Test;

public class CheckOwnerOfPitTest {

    private CheckOwnerOfPit underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new CheckOwnerOfPit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPitDoesNotBelongToPlayer() {
        //Given
        int pit = 7; //Player B's pit
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.A, pit);
    }

    @Test
    public void shouldNotThrowExceptionWhenPitBelongsToUser(){
        //Given
        int pit = 5;
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.A, pit);
    }
}