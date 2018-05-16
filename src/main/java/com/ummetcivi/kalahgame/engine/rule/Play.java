package com.ummetcivi.kalahgame.engine.rule;

import com.ummetcivi.kalahgame.constant.Constants;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;

public class Play extends Rule {
    @Override
    void execute(final Game game, final Player player, final int pit) {
        game.play(board -> {

            int lastPit = distribute(game, player, pit, board);

            GameStatus gameStatus = game.getGameStatus();
            if (lastPit != game.getKalahIndex(player)) {
                gameStatus = game.getGameStatus().getNextTurn();
            }

            return gameStatus;
        });
    }

    private int distribute(Game game, Player player, int lastPit, int[] board) {
        int stoneCount = board[lastPit];
        board[lastPit] = 0;

        for (int i = 0; i < stoneCount; i++) {
            lastPit = calculatePitIndex(game, player, lastPit);

            if (i == stoneCount - 1 && isEligibleForCollectingOppositePit(game, player, board, lastPit)) {
                handleWhenLastPitIsEmpty(game, player, board, lastPit);
            } else {
                board[lastPit]++;
            }
        }

        return lastPit;
    }

    private boolean isEligibleForCollectingOppositePit(Game game, Player player, int[] board, int pit) {
        return game.isPitEmpty(pit) && game.playerOwnsPit(player, pit) && board[getOppositePit(pit)] > 0;
    }

    private void handleWhenLastPitIsEmpty(Game game, Player player, int[] board, int pit) {
        int kalahIndex = game.getKalahIndex(player);

        board[kalahIndex] += board[pit];
        board[pit] = 0;

        int oppositePitIndex = getOppositePit(pit);
        board[kalahIndex] += board[oppositePitIndex];
        board[oppositePitIndex] = 0;
    }

    private int calculatePitIndex(Game game, Player player, int pit) {
        int result = (pit + 1) % Constants.BOARD_LENGTH;

        if (game.getKalahIndex(player.opponent()) == result) {
            result++;
        }
        return result;
    }

    private int getOppositePit(int pit) {
        return Constants.TOTAL_NUMBER_OF_PITS - pit;
    }
}
