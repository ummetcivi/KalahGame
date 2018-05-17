package com.ummetcivi.kalahgame.domain;

import com.ummetcivi.kalahgame.constant.Constants;

import java.util.stream.IntStream;

public class Board {

    private final int[] board;

    public Board() {
        board = new int[Constants.BOARD_LENGTH];
        prepareBoard();
    }

    public int getStoneCount(int pit) {
        return board[pit];
    }

    public void emptyPit(int pit) {
        board[pit] = 0;
    }

    public void addStone(int pit) {
        board[pit]++;
    }

    public void addStone(int pit, int amount) {
        board[pit] += amount;
    }

    public int[] of(Player player) {
        int startIndex = getStartIndex(player);
        int length = getKalahIndex(player) - startIndex;

        int[] playersBoard = new int[length];

        System.arraycopy(board, startIndex, playersBoard, 0, length);

        return playersBoard;
    }

    int getScore(Player player) {
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

    private void prepareBoard() {
        IntStream.range(0, getKalahIndex(Player.B))
                .filter(value -> value != getKalahIndex(Player.A))
                .forEach(value -> board[value] = Constants.NUMBER_OF_STONES);
    }
}
