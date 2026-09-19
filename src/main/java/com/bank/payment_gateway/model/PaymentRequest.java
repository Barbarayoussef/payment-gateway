package com.bank.payment_gateway.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private String cardNumber;
    private double amount;
    private String merchantId;
}
