package com.ummetcivi.kalahgame.controller.resource;

import com.ummetcivi.kalahgame.domain.GameStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameResource {
    private String id;
    private String playerId;
    private int[] board;
    private int[] opponentBoard;
    private int score;
    private int opponentScore;
    private GameStatus gameStatus;
}
