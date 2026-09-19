package com.bank.payment_gateway.model;

import lombok.Data;

@Data
public class Card {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private double balance;
}
