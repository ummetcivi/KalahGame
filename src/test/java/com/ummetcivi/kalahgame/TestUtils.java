package com.ummetcivi.kalahgame;

import com.ummetcivi.kalahgame.domain.Board;
import com.ummetcivi.kalahgame.domain.Game;
import org.springframework.test.util.ReflectionTestUtils;

public final class TestUtils {

    private TestUtils() {
    }

    public static Game prepareJoinedGame() {
        Game game = new Game();
        game.join();
        return game;
    }

    public static Game prepareGameWithGivenBoardArray(int[] innerBoard) {
        Board board = createBoardWithGivenArray(innerBoard);
        Game game = prepareJoinedGame();
        ReflectionTestUtils.setField(game, "board", board);
        return game;
    }

    private static Board createBoardWithGivenArray(int[] innerBoard) {
        Board board = new Board();
        ReflectionTestUtils.setField(board, "board", innerBoard);
        return board;
    }
}
