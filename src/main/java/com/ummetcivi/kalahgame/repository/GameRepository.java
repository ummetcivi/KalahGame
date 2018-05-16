package com.ummetcivi.kalahgame.repository;

import com.ummetcivi.kalahgame.domain.Game;

public interface GameRepository {
    Game save(Game game);

    Game findById(String id);

    Game findByPlayerId(String playerId);
}
