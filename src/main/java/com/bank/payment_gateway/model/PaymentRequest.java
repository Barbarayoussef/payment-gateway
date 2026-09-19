package com.bank.payment_gateway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table (name = "cards")
@Data
public class PaymentRequest {
    @Id
    private String cardNumber;
    private double amount;
    private String merchantId;
}
