package com.ummetcivi.kalahgame.service;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;
import com.ummetcivi.kalahgame.executor.CommandChainExecutor;
import com.ummetcivi.kalahgame.repository.GameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GameServiceTest {

    @InjectMocks
    private GameService underTest;
    @Mock
    private GameRepository gameRepositoryMock;
    @Mock
    private CommandChainExecutor commandChainExecutorMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateGameAndCallSaveThenReturn() {
        Game game = new Game();

        Mockito.when(gameRepositoryMock.save(Mockito.any())).thenReturn(game);

        Game result = underTest.createGame();

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getPlayerAId());
        Assert.assertNotNull(result.getPlayerBId());
        Mockito.verify(gameRepositoryMock).save(Mockito.any());
        Assert.assertEquals(GameStatus.WAITING_OPPONENT, result.getGameStatus());
    }

    @Test
    public void shouldJoinAndSaveGameThenReturnWhenGameExists() {
        Game game = new Game();

        Mockito.when(gameRepositoryMock.findById(game.getId())).thenReturn(game);
        Mockito.when(gameRepositoryMock.save(game)).thenReturn(game);

        Game result = underTest.join(game.getId());

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getPlayerBId());
        Assert.assertEquals(GameStatus.TURN_A, result.getGameStatus());
        Mockito.verify(gameRepositoryMock).save(game);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenJoiningNonExistentGame() {
        underTest.join("anyId");
    }

    @Test
    public void shouldGetGameByPlayerId() {
        Game game = new Game();

        Mockito.when(gameRepositoryMock.findByPlayerId(game.getPlayerAId())).thenReturn(game);

        Game gameByPlayerId = underTest.getGameByPlayerId(game.getPlayerAId());

        Assert.assertEquals(game, gameByPlayerId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenGameByPlayerIdIsNull() {
        Game game = new Game();

        underTest.getGameByPlayerId(game.getPlayerAId());
    }

    @Test
    public void shouldPlayWhenGameExists() {
        Game game = new Game();
        int pit = 0;

        Mockito.when(gameRepositoryMock.findByPlayerId(game.getPlayerAId())).thenReturn(game);
        Mockito.when(gameRepositoryMock.save(game)).thenReturn(game);

        Game result = underTest.play(game.getPlayerAId(), pit);

        Assert.assertEquals(game, result);
        Mockito.verify(gameRepositoryMock).save(game);
        Mockito.verify(commandChainExecutorMock).execute(game, Player.A, pit);
    }
}