package com.ummetcivi.kalahgame.repository.impl;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.repository.GameRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGameRepository implements GameRepository {
    private final Map<String, Game> idGameMap;
    private final Map<String, Game> playerIdGameMap;

    public InMemoryGameRepository() {
        this.idGameMap = new HashMap<>();
        this.playerIdGameMap = new HashMap<>();
    }

    @Override
    public Game save(Game game) {
        playerIdGameMap.putIfAbsent(game.getPlayerAId(), game);
        playerIdGameMap.putIfAbsent(game.getPlayerBId(), game);
        idGameMap.putIfAbsent(game.getId(), game);
        return game;
    }

    @Override
    public Game findById(String id) {
        return idGameMap.get(id);
    }

    @Override
    public Game findByPlayerId(String playerId) {
        return playerIdGameMap.get(playerId);
    }
}
