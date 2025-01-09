package com.example.gameserviceapi.controller;

import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Game> saveGame(@RequestHeader("userIdRequest") String userId, @RequestBody Game game) {
        Game gameCreated = gameService.create(userId, game);
        return ResponseEntity.ok(gameCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> findGame(@PathVariable Long id) {
        Optional<Game> byId = gameService.findById(id);
        return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
