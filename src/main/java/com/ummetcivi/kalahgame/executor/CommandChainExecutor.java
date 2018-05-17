package com.ummetcivi.kalahgame.executor;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import com.ummetcivi.kalahgame.executor.command.*;
import org.springframework.stereotype.Component;

@Component
public class CommandChainExecutor {

    private final Command command;

    public CommandChainExecutor() {
        command = new CheckTurn();
        command.setNext(new CheckOwnerOfPit())
                .setNext(new CheckPitIsNotEmpty())
                .setNext(new Play())
                .setNext(new CheckEnd());
    }

    public void execute(Game game, Player player, int pit) {
        command.execute(game, player, pit);
    }
}
