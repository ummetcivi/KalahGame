package com.ummetcivi.kalahgame.engine.rule;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckPitIsNotEmpty extends Rule {
    @Override
    void execute(Game game, Player player, int pit) {
        if (game.isPitEmpty(pit)) {
            throw new IllegalArgumentException("Slot is empty, please select another slot!");
        }
    }
}
