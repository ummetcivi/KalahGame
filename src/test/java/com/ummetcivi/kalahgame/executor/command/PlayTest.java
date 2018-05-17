package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.TestUtils;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayTest {

    private Play underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new Play();
    }

    @Test
    public void shouldNotChangeTurnIfLastStoneHitsKalah() {
        //Given ({6,6,6,6,6,6,0,...}
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.A, 0);

        //Then
        Assert.assertEquals(GameStatus.TURN_A, game.getGameStatus());
    }

    @Test
    public void shouldChangeTurnIfLastStoneDoesNotHitKalah() {
        //Given ({6,6,6,6,6,6,0,...}
        Game game = TestUtils.prepareJoinedGame();

        //When
        underTest.run(game, Player.A, 1);

        //Then
        Assert.assertEquals(GameStatus.TURN_B, game.getGameStatus());
    }

    @Test
    public void shouldDistributeStones() {
        //Given
        Game game = TestUtils.prepareJoinedGame();
        int pit = 1;

        //When
        underTest.run(game, Player.A, pit);

        //Then
        Assert.assertEquals(0, game.board().getStoneCount(pit));
        for (int i = 2; i < 6; i++) {
            Assert.assertEquals(7, game.board().getStoneCount(i));
        }
        Assert.assertEquals(7, game.board().getStoneCount(7)); //First pit of PlayerB
        Assert.assertEquals(1, game.getScoreOf(Player.A));
    }

    @Test
    public void shouldSkipWhenStoneHitsOpponentsKalah() {
        //Given
        int score = 1;
        int opponentScore = 3;
        int pit = 5;
        int[] board = {5, 6, 5, 5, 5, 9, score, 5, 4, 3, 5, 6, 4, opponentScore};
        Game game = TestUtils.prepareGameWithGivenBoardArray(board);

        //When
        underTest.run(game, Player.A, pit);

        //Then
        Assert.assertEquals(opponentScore, game.getScoreOf(Player.A.opponent()));
        Assert.assertEquals(6, game.board().getStoneCount(0));
        Assert.assertEquals(7, game.board().getStoneCount(1));
    }

    @Test
    public void shouldCollectOppositeStonesAndLastStoneWhenLastStoneHitsEmptyPitAndOppositePitIsNotEmpty() {
        //Given
        int score = 6;
        int oppositeStoneCount = 5;
        int pit = 0;
        int emptyPit = 2;
        int oppositePit = 10;
        int[] board = {2, 1, 0, 4, 3, 5, score, 4, 5, 6, oppositeStoneCount, 2, 4, 3};
        Game game = TestUtils.prepareGameWithGivenBoardArray(board);

        //When
        underTest.run(game, Player.A, pit);

        //Then
        Assert.assertEquals(oppositeStoneCount + score + 1, game.getScoreOf(Player.A));
        Assert.assertEquals(0, game.board().getStoneCount(oppositePit));
        Assert.assertEquals(0, game.board().getStoneCount(emptyPit));
    }

}