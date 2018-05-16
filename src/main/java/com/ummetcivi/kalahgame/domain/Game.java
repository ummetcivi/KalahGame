package com.ummetcivi.kalahgame.domain;

import com.ummetcivi.kalahgame.constant.Constants;
import com.ummetcivi.kalahgame.util.RandomIdGenerator;
import lombok.Getter;

import java.util.function.Function;
import java.util.stream.IntStream;

public class Game {
    @Getter
    private final String id;
    @Getter
    private final String playerAId;
    @Getter
    private final String playerBId;
    private final int[] board;
    @Getter
    private GameStatus gameStatus = GameStatus.WAITING_OPPONENT;

    public Game() {
        this.id = RandomIdGenerator.generateId();
        this.playerAId = RandomIdGenerator.generateId();
        this.playerBId = RandomIdGenerator.generateId();
        this.board = new int[Constants.BOARD_LENGTH];
        prepareBoard();
    }

    public void join() {
        if (!GameStatus.WAITING_OPPONENT.equals(this.gameStatus)) {
            throw new IllegalArgumentException("Game has either ended or no room left!");
        }

        this.gameStatus = GameStatus.TURN_A;
    }

    public void play(Function<int[], GameStatus> playFunction) {
        this.gameStatus = playFunction.apply(board);
    }

    public void markIfEnded(Function<int[], GameStatus> endFunction) {
        this.gameStatus = endFunction.apply(board);
    }

    public int[] getBoard(Player player) {
        int startIndex = getStartIndex(player);
        int length = getKalahIndex(player) - startIndex;

        int[] playersBoard = new int[length];

        System.arraycopy(board, startIndex, playersBoard, 0, length);

        return playersBoard;
    }

    public int getScore(Player player) {
        int scoreIndex = getKalahIndex(player);

        return board[scoreIndex];
    }

    public int getStartIndex(Player player) {
        return Player.A == player ? 0 : Constants.BOARD_LENGTH / 2;
    }

    public int getKalahIndex(Player player) {
        return (Player.A == player ? Constants.BOARD_LENGTH / 2 : Constants.BOARD_LENGTH) - 1;
    }

    public boolean playerOwnsPit(Player player, int pit) {
        return pit >= getStartIndex(player) && pit < getKalahIndex(player);
    }

    public boolean isPitEmpty(int pit) {
        return board[pit] == 0;
    }

    public Player getPlayer(String playerId) {
        if (this.playerAId.equals(playerId)) {
            return Player.A;
        } else if (this.playerBId.equals(playerId)) {
            return Player.B;
        } else {
            throw new IllegalArgumentException("");
        }
    }

    private void prepareBoard() {
        IntStream.range(0, getKalahIndex(Player.B))
                .filter(value -> value != getKalahIndex(Player.A))
                .forEach(value -> board[value] = Constants.NUMBER_OF_STONES);
    }
}
