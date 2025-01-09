package com.example.gameserviceapi.services;

import com.example.gameserviceapi.entities.Game;
import com.example.gameserviceapi.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final StreamBridge streamBridge;

    public static final String GAME_CREATED_TOPIC = "gameBinding-out-0";

    public Game create(String userId, Game game) {
        game.setUserId(Integer.valueOf(userId));
        return Optional.of(game)
                .map(gameRepository::save)
                .map(this::sendGameEvent)
                .orElseThrow(() -> new RuntimeException("Error saving game"));
    }

    private Game sendGameEvent(Game game) {
        Optional.of(game)
                .map(givenGame -> streamBridge.send(GAME_CREATED_TOPIC, givenGame))
                .map(bool -> game);

        return game;
    }

    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }
}
