package com.stripe.stripepayments.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Currency;

@Entity
@Table(name = "payments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String paymentIntentId;
    private String customerId;
    private String productId;
    private Long amount;
    private String currency;

    @Enumerated(EnumType.STRING)
    private StripeEventType type;

}
