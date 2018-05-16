package com.ummetcivi.kalahgame.engine.rule;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckEnd extends Rule {
    @Override
    void execute(Game game, Player player, int pit) {
        game.markIfEnded(board -> {
            boolean ended = isEnded(game, player, board) || isEnded(game, player.opponent(), board);

            if (!ended) {
                return game.getGameStatus();
            }

            return findWinner(game, player);
        });
    }

    private boolean isEnded(Game game, Player player, int[] board) {
        boolean ended = true;

        for (int i = game.getStartIndex(player); i < game.getKalahIndex(player) && ended; i++) {
            if (board[i] > 0) {
                ended = false;
            }
        }
        return ended;
    }

    private GameStatus findWinner(Game game, Player player) {
        if (game.getScore(player) > game.getScore(player.opponent())) {
            return GameStatus.getWinOf(player);
        } else {
            return GameStatus.getWinOf(player.opponent());
        }
    }
}
