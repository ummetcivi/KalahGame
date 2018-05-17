package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.TestUtils;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CommandTest {

    @Spy
    private Command underTest;
    @Mock
    private Command nextCommandMock;

    @Before
    public void setUp() {
        underTest = new Command() {
            @Override
            void run(Game game, Player player, int pit) {

            }
        };

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldExecuteAndCallNextCommandWhenNextCommandExists() {
        //Given
        Game game = TestUtils.prepareJoinedGame();
        underTest.setNext(nextCommandMock);
        int pit = 0;

        //When
        underTest.execute(game, Player.A, pit);

        //Then
        Mockito.verify(underTest).run(game, Player.A, pit);
        Mockito.verify(nextCommandMock).execute(game, Player.A, pit);
        Mockito.verify(nextCommandMock).run(game, Player.A, pit);
    }

    public void shouldNotExecuteNextWhenNextCommandIsNull() {
        //Given
        Game game = TestUtils.prepareJoinedGame();
        int pit = 0;

        //When
        underTest.execute(game, Player.A, pit);

        //Then
        Mockito.verify(underTest).run(game, Player.A, pit);
        Mockito.verify(nextCommandMock, Mockito.never()).execute(game, Player.A, pit);
    }
}