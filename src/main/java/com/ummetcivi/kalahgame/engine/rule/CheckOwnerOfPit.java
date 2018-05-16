package com.ummetcivi.kalahgame.engine.rule;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckOwnerOfPit extends Rule {
    @Override
    void execute(Game game, Player player, int pit) {
        if (!game.playerOwnsPit(player, pit)) {
            throw new IllegalArgumentException("That pit doesn't belong to you!");
        }
    }
}
