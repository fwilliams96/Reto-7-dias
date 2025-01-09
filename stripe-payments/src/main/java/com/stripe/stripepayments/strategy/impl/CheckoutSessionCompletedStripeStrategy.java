package com.stripe.stripepayments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.stripepayments.entities.PaymentModel;
import com.stripe.stripepayments.entities.StripeEventType;
import com.stripe.stripepayments.repositories.PaymentRepository;
import com.stripe.stripepayments.strategy.StripeStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckoutSessionCompletedStripeStrategy implements StripeStrategy {

    private final PaymentRepository paymentRepository;

    @Override
    public boolean isApplicable(Event event) {
        return StripeEventType.CHECKOUT_SESSION_COMPLETED.getValue().equalsIgnoreCase(event.getType());
    }

    @Override
    public Event process(Event event) {
        var session = this.deserialize(event);
        return Optional.of(event)
                .map(givenEvent -> paymentRepository.findByPaymentIntentId(session.getPaymentIntent()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(payment -> setProductId(payment, session.getMetadata().get("product_id")))
                .map(paymentRepository::save)
                .map(givenPayment -> event)
                .orElseThrow(() -> new RuntimeException("Error processing"));
    }

    private PaymentModel setProductId(PaymentModel payment, String productId) {
        payment.setProductId(productId);
        payment.setType(StripeEventType.CHECKOUT_SESSION_COMPLETED);
        return payment;
    }

    private Session deserialize(Event event) {
        return (Session) event.getDataObjectDeserializer().getObject()
                .orElseThrow(() -> new RuntimeException("Error deserializing"));
    }
}
