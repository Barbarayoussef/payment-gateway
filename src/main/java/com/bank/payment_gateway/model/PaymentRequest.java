package com.bank.payment_gateway.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotBlank(message = "Card number cannot be blank")
    private String cardNumber;

    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @NotBlank(message = "Merchant ID is required")
    private String merchantId;
}
