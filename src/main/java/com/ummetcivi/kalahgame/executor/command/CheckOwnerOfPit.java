package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;

public class CheckOwnerOfPit extends Command {
    @Override
    void run(Game game, Player player, int pit) {
        if (!game.board().playerOwnsPit(player, pit)) {
            throw new IllegalArgumentException("That pit doesn't belong to you!");
        }
    }
}
