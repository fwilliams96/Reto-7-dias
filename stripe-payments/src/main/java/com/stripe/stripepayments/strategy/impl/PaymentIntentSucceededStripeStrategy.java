package com.stripe.stripepayments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.stripepayments.entities.PaymentModel;
import com.stripe.stripepayments.entities.StripeEventType;
import com.stripe.stripepayments.repositories.PaymentRepository;
import com.stripe.stripepayments.strategy.StripeStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentIntentSucceededStripeStrategy implements StripeStrategy {

    private final PaymentRepository paymentRepository;

    @Override
    public boolean isApplicable(Event event) {
        return StripeEventType.PAYMENT_INTENT_SUCCEED.getValue().equalsIgnoreCase(event.getType());
    }

    @Override
    public Event process(Event event) {
        return Optional.of(event)
                .map(this::deserialize)
                .map(this::mapToEntity)
                .map(paymentRepository::save)
                .map(given -> event)
                .orElseThrow(() -> new RuntimeException("Error processing"));
    }

    private PaymentModel mapToEntity(PaymentIntent paymentIntent) {
        return PaymentModel.builder()
                .paymentIntentId(paymentIntent.getId())
                .customerId(paymentIntent.getCustomer())
                .amount(paymentIntent.getAmount())
                .currency(paymentIntent.getCurrency())
                .type(StripeEventType.PAYMENT_INTENT_SUCCEED)
                .build();
    }

    private PaymentIntent deserialize(Event event) {
        return (PaymentIntent) event.getDataObjectDeserializer().getObject()
                .orElseThrow(() -> new RuntimeException("Error deserializing object"));
    }
}
