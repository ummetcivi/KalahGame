package com.ummetcivi.kalahgame.repository.impl;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.repository.GameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryGameRepositoryTest {

    private GameRepository underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new InMemoryGameRepository();
    }

    @Test
    public void shouldSaveGameThenReturn() {
        Game game = new Game();

        Game savedGame = underTest.save(game);

        Assert.assertEquals(game, savedGame);
    }

    @Test
    public void shouldFindGameByIdThenReturn() {
        Game game = new Game();
        underTest.save(game);

        Game result = underTest.findById(game.getId());

        Assert.assertEquals(game, result);
    }

    @Test
    public void shouldFindGameByPlayerId() {
        Game game = new Game();
        underTest.save(game);

        Game resultByPlayerAId = underTest.findByPlayerId(game.getPlayerAId());
        Game resultByPlayerBId = underTest.findByPlayerId(game.getPlayerBId());

        Assert.assertEquals(game, resultByPlayerAId);
        Assert.assertEquals(game, resultByPlayerBId);

    }
}