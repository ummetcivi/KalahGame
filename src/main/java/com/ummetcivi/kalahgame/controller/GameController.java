package com.ummetcivi.kalahgame.controller;

import com.ummetcivi.kalahgame.controller.converter.GameToGameResourceConverter;
import com.ummetcivi.kalahgame.controller.resource.GameResource;
import com.ummetcivi.kalahgame.domain.Game;
import com.ummetcivi.kalahgame.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(path = "/api/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    private static final String PLAYER_ID_HEADER = "playerId";
    private final GameService gameService;
    private final GameToGameResourceConverter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GameResource> createGame() {
        Game game = gameService.createGame();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(converter.convert(game, game.getPlayerAId()));
    }

    @PutMapping("/{gameId}/join")
    public ResponseEntity<GameResource> joinGame(@PathVariable String gameId) {
        Game game = gameService.join(gameId);
        return ResponseEntity.ok()
                .body(converter.convert(game, game.getPlayerBId()));
    }

    @GetMapping
    public ResponseEntity<GameResource> getGame(@RequestHeader(PLAYER_ID_HEADER) String playerId) {
        Game game = gameService.getGameByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(converter.convert(game, playerId));
    }

    @PutMapping("/play")
    public ResponseEntity<GameResource> play(@RequestHeader(PLAYER_ID_HEADER) String playerId, @RequestParam int slot) {
        Game game = gameService.play(playerId, slot);

        return ResponseEntity.ok()
                .body(converter.convert(game, playerId));
    }
}