package com.ummetcivi.kalahgame.domain;

import com.ummetcivi.kalahgame.util.RandomIdGenerator;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.function.BiFunction;

public class Game {

    @Getter
    private final String id;
    @Getter
    private final String playerAId;
    @Getter
    private final String playerBId;
    @Getter
    @Accessors(fluent = true)
    private final Board board;
    @Getter
    private GameStatus gameStatus = GameStatus.WAITING_OPPONENT;

    public Game() {
        this.id = RandomIdGenerator.generateId();
        this.playerAId = RandomIdGenerator.generateId();
        this.playerBId = RandomIdGenerator.generateId();
        this.board = new Board();
    }

    public void join() {
        if (!GameStatus.WAITING_OPPONENT.equals(this.gameStatus)) {
            throw new IllegalArgumentException("Game has either ended or no room left!");
        }
        this.gameStatus = GameStatus.TURN_A;
    }

    public void play(BiFunction<Board, GameStatus, GameStatus> playFunction) {
        this.gameStatus = playFunction.apply(board, this.gameStatus);
    }

    public void markIfEnded(BiFunction<Board, GameStatus, GameStatus> endFunction) {
        this.gameStatus = endFunction.apply(board, this.gameStatus);
    }

    public int getScoreOf(Player player) {
        return board.getScore(player);
    }

    public Player getPlayer(String playerId) {
        if (this.playerAId.equals(playerId)) {
            return Player.A;
        } else if (this.playerBId.equals(playerId)) {
            return Player.B;
        } else {
            throw new IllegalArgumentException("Player not found");
        }
    }
}
