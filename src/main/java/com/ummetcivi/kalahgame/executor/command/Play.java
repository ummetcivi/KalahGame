package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.constant.Constants;
import com.ummetcivi.kalahgame.domain.Board;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;

public class Play extends Command {

    @Override
    void run(final Game game, final Player player, final int pit) {
        game.play((board, gameStatus) -> {

            int lastPit = distribute(board, player, pit);

            if (lastPit != board.getKalahIndex(player)) {
                return gameStatus.getNextTurn();
            }

            return gameStatus;
        });
    }

    private int distribute(Board board, Player player, int lastPit) {
        int stoneCount = board.getStoneCount(lastPit);
        board.emptyPit(lastPit);

        for (int i = 0; i < stoneCount; i++) {
            lastPit = calculatePitIndex(board, player, lastPit);

            if (i == stoneCount - 1 && isEligibleForCollectingOppositePit(player, board, lastPit)) {
                handleWhenLastPitIsEmpty(player, board, lastPit);
            } else {
                board.addStone(lastPit);
            }
        }

        return lastPit;
    }

    private boolean isEligibleForCollectingOppositePit(Player player, Board board, int pit) {
        return board.isPitEmpty(pit) && board.playerOwnsPit(player, pit)
                && board.getStoneCount(getOppositePit(pit)) > 0;
    }

    private void handleWhenLastPitIsEmpty(Player player, Board board, int pit) {
        int kalahIndex = board.getKalahIndex(player);

        board.addStone(kalahIndex);
        board.emptyPit(pit);

        int oppositePitIndex = getOppositePit(pit);
        board.addStone(kalahIndex, board.getStoneCount(oppositePitIndex));
        board.emptyPit(oppositePitIndex);
    }

    private int calculatePitIndex(Board board, Player player, int pit) {
        int result = (pit + 1) % Constants.BOARD_LENGTH;

        if (board.getKalahIndex(player.opponent()) == result) {
            result = (result + 1) % Constants.BOARD_LENGTH;
        }
        return result;
    }

    private int getOppositePit(int pit) {
        return Constants.TOTAL_NUMBER_OF_PITS - pit;
    }
}
