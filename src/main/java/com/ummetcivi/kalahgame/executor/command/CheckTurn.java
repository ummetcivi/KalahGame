package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.GameStatus;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckTurn extends Command {
    @Override
    void run(Game game, Player player, int pit) {
        if(GameStatus.getTurnOf(player) != game.getGameStatus()){
            throw new IllegalArgumentException("It's not your turn yet! Please be patient :)");
        }

    }
}
