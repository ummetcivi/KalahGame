package com.ummetcivi.kalahgame.executor.command;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;

public abstract class Command {
    private Command next;

    abstract void run(Game game, Player player, int pit);

    public final void execute(Game game, Player player, int pit) {
        run(game, player, pit);
        if (next != null) {
            next.execute(game, player, pit);
        }
    }

    public final Command setNext(Command next) {
        this.next = next;
        return next;
    }
}
