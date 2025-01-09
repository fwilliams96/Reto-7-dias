package com.stripe.stripepayments.repositories;

import com.stripe.stripepayments.entities.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {

    Optional<PaymentModel> findByPaymentIntentId(String paymentIntentId);

}
