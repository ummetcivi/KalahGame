package com.ummetcivi.kalahgame.domain;

public enum Player {
    A, B;

    public Player opponent() {
        return this == A ? B : A;
    }
}
