package com.ummetcivi.kalahgame.engine;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import com.ummetcivi.kalahgame.engine.rule.*;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {
    private final Rule rule;

    public GameEngine() {
        rule = new CheckTurn();
        rule.setNext(new CheckOwnerOfPit())
                .setNext(new CheckOwnerOfPit())
                .setNext(new Play())
                .setNext(new CheckEnd());
    }

    public void play(Game game, String playerId, int pit) {
        Player player = game.getPlayer(playerId);

        pit += game.getStartIndex(player);

        rule.run(game, player, pit);
    }
}
