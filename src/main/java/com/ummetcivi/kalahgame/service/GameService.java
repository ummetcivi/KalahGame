package com.ummetcivi.kalahgame.service;

import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.engine.GameEngine;
import com.ummetcivi.kalahgame.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameService {
    private final GameRepository gameRepository;
    private final GameEngine gameEngine;

    public Game createGame() {
        Game game = new Game();

        return gameRepository.save(game);
    }

    public Game join(String gameId) {
        Game game = gameRepository.findById(gameId);

        if (game == null) {
            throw new IllegalArgumentException("Game not found");
        }

        game.join();

        return gameRepository.save(game);
    }

    public Game getGameByPlayerId(String playerId) {
        Game game = gameRepository.findByPlayerId(playerId);

        if (game == null) {
            throw new IllegalArgumentException("Game not found!");
        }

        return game;
    }

    public Game play(String playerId, int slot) {
        Game game = getGameByPlayerId(playerId);

        gameEngine.play(game, playerId, slot);

        gameRepository.save(game);

        return game;
    }
}
