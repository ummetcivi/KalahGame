package com.ummetcivi.kalahgame.domain;

public enum GameStatus {
    TURN_A,
    TURN_B,
    WAITING_OPPONENT,
    WIN_A,
    WIN_B;

    public static GameStatus getWinOf(Player player) {
        return player == Player.A ? WIN_A : WIN_B;
    }

    public static GameStatus getTurnOf(Player player) {
        return player == Player.A ? TURN_A : TURN_B;
    }

    public GameStatus getNextTurn() {
        return this == TURN_A ? TURN_B : TURN_A;
    }
}
