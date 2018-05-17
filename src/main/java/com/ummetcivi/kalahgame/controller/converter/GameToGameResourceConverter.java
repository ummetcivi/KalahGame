package com.ummetcivi.kalahgame.controller.converter;

import com.ummetcivi.kalahgame.controller.resource.GameResource;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.domain.Player;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class GameToGameResourceConverter {

    public GameResource convert(@NotNull Game source, String playerId) {
        Player player = source.getPlayer(playerId);

        return GameResource.builder()
                .gameStatus(source.getGameStatus())
                .id(source.getId())
                .playerId(playerId)
                .board(source.board().of(player))
                .opponentBoard(source.board().of(player.opponent()))
                .score(source.getScoreOf(player))
                .opponentScore(source.getScoreOf(player.opponent()))
                .build();
    }
}