package com.bank.payment_gateway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cards")
@Data
public class Card {
    @Id
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private double balance;
}
