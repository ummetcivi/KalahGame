package com.ummetcivi.kalahgame.engine.rule;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckTurn extends Rule {
    @Override
    void execute(Game game, Player player, int pit) {
        if(GameStatus.getTurnOf(player) != game.getGameStatus()){
            throw new IllegalArgumentException("It's not your turn yet! Please be patient :)");
        }

    }
}
