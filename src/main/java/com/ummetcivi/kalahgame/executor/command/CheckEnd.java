package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.domain.Board;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckEnd extends Command {

    @Override
    void run(Game game, Player player, int pit) {
        game.markIfEnded((board, gameStatus) -> {
            boolean ended = isEnded(player, board) || isEnded(player.opponent(), board);

            if (!ended) {
                return game.getGameStatus();
            }

            return findWinner(game, player);
        });
    }

    private boolean isEnded(Player player, Board board) {
        boolean ended = true;

        for (int i = board.getStartIndex(player); i < board.getKalahIndex(player) && ended; i++) {
            if (board.getStoneCount(i) > 0) {
                ended = false;
            }
        }
        return ended;
    }

    private GameStatus findWinner(Game game, Player player) {
        if (game.getScoreOf(player) > game.getScoreOf(player.opponent())) {
            return GameStatus.getWinOf(player);
        } else {
            return GameStatus.getWinOf(player.opponent());
        }
    }
}
