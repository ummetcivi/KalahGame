package com.ummetcivi.kalahgame.engine.rule;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;

public abstract class Rule {
    private Rule next;

    abstract void execute(Game game, Player player, int pit);

    public final void run(Game game, Player player, int pit) {
        execute(game, player, pit);
        if (next != null) {
            next.run(game, player, pit);
        }
    }

    public final Rule setNext(Rule next) {
        this.next = next;
        return next;
    }
}
