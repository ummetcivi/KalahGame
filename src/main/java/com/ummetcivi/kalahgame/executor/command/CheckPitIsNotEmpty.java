package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckPitIsNotEmpty extends Command {
    @Override
    void run(Game game, Player player, int pit) {
        if (game.board().isPitEmpty(pit)) {
            throw new IllegalArgumentException("Pit is empty, please select another pit!");
        }
    }
}
