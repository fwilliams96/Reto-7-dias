package com.example.eligibility_micro.services.impl;

import com.example.eligibility_micro.domain.GameCreatedEvent;
import com.example.eligibility_micro.domain.GameEligibleEvent;
import com.example.eligibility_micro.services.EligibleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EligibilityServiceImpl implements EligibleService {

    @Override
    public Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> GameEligibleEvent.builder()
                        .id(givenCreated.getId())
                        .name(givenCreated.getName())
                        .userId(givenCreated.getUserId())
                        .isEligible(true)
                        .build()
                );
    }

    private Mono<GameCreatedEvent> checkIsEligible(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .filter(given -> !given.getName().isBlank())
                .map(given -> gameCreatedEvent);

    }
}
