package com.example.eligibility_micro.services;

import com.example.eligibility_micro.domain.GameCreatedEvent;
import com.example.eligibility_micro.domain.GameEligibleEvent;
import reactor.core.publisher.Mono;

public interface EligibleService {

    Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent);

}
