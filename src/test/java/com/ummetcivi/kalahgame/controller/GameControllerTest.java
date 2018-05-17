package com.ummetcivi.kalahgame.controller;

import com.ummetcivi.kalahgame.TestUtils;
import com.ummetcivi.kalahgame.controller.converter.GameToGameResourceConverter;
import com.ummetcivi.kalahgame.controller.resource.GameResource;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.service.GameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GameControllerTest {

    @InjectMocks
    private GameController underTest;
    @Mock
    private GameService gameServiceMock;
    @Mock
    private GameToGameResourceConverter converterMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateGameAndReturnHttpCreated() {
        Game game = new Game();
        Mockito.when(gameServiceMock.createGame()).thenReturn(game);

        ResponseEntity<GameResource> resultEntity = underTest.createGame();
        Assert.assertEquals(HttpStatus.CREATED, resultEntity.getStatusCode());
    }

    @Test
    public void shouldJoinGameAndReturnHttpOk() {
        //When
        Game game = new Game();
        Mockito.when(gameServiceMock.join(game.getId())).thenReturn(game);
        ResponseEntity<GameResource> resultEntity = underTest.joinGame(game.getId());
        //Then
        Assert.assertEquals(HttpStatus.OK, resultEntity.getStatusCode());
    }

    @Test
    public void shouldGetGameWithGivenId() {
        //When
        Game game = new Game();

        Mockito.when(gameServiceMock.getGameByPlayerId(game.getPlayerAId())).thenReturn(game);
        ResponseEntity<GameResource> resultEntity = underTest.getGame(game.getPlayerAId());

        Assert.assertEquals(HttpStatus.OK, resultEntity.getStatusCode());
    }

    @Test
    public void shouldPlayGameAndReturnHttpOk() {
        //When
        Game game = TestUtils.prepareJoinedGame();

        Mockito.when(gameServiceMock.play(game.getPlayerAId(), 0)).thenReturn(game);
        ResponseEntity<GameResource> resultEntity = underTest.play(game.getPlayerAId(), 0);

        Assert.assertEquals(HttpStatus.OK, resultEntity.getStatusCode());
    }
}