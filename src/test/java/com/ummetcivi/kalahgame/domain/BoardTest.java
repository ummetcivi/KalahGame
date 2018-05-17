package com.ummetcivi.kalahgame.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

    private Board underTest;

    @Before
    public void setUp() {
        underTest = new Board();
    }

    @Test
    public void shouldGetPlayersBoard() {
        //Given
        int[] playersBoard = {7, 6, 6, 6, 6, 6};
        underTest.addStone(0);

        //When
        int[] result = underTest.of(Player.A);

        //Then
        Assert.assertArrayEquals(playersBoard, result);
    }

    @Test
    public void shouldReturnTrueWhenPlayerOwnsGivenPit() {
        final int pit = 4;

        Assert.assertTrue(underTest.playerOwnsPit(Player.A, pit));
    }

    @Test
    public void shouldReturnFalseWhenPlayerDoesNotOwnGivenPit() {
        final int pit = 6;

        Assert.assertFalse(underTest.playerOwnsPit(Player.A, pit));
    }
}