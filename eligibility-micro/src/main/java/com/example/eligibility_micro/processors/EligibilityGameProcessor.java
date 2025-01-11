package com.example.eligibility_micro.processors;

import com.example.eligibility_micro.domain.GameCreatedEvent;
import com.example.eligibility_micro.domain.GameEligibleEvent;
import com.example.eligibility_micro.services.EligibleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class EligibilityGameProcessor {

    private final EligibleService eligibleService;

    public Flux<GameEligibleEvent> process(Flux<GameCreatedEvent> gameCreatedEventFlux) {
        return gameCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .flatMap(eligibleService::eligibilityGame)
                .onErrorContinue(this::handleError);
    }

    private void handleError(Throwable throwable, Object o) {
        log.error("Error processing event: {}", o, throwable);
    }

}
