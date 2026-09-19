package com.bank.payment_gateway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "cards")
@Data
public class Card {
    @Id
    @NotBlank(message = "Card number is required")
    private String cardNumber;

    @NotBlank(message = "Cardholder name is required")
    private String cardHolderName;

    @NotBlank(message = "Expiry date is required")
    private String expiryDate;

    @NotBlank(message = "CVV is required")
    private String cvv;

    @Positive(message = "Initial balance must be positive")
    private double balance;
}
