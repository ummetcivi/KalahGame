package com.ummetcivi.kalahgame.controller.converter;

import com.ummetcivi.kalahgame.controller.resource.GameResource;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class GameToGameResourceConverter {
    public GameResource convert(Game source, String playerId) {
        Player player = source.getPlayer(playerId);

        return GameResource.builder()
                .gameStatus(source.getGameStatus())
                .id(source.getId())
                .playerId(playerId)
                .board(source.getBoard(player))
                .opponentBoard(source.getBoard(player.opponent()))
                .score(source.getScore(player))
                .opponentScore(source.getScore(player.opponent()))
                .build();
    }
}