package com.bank.payment_gateway.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private double amount;
    private String status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
}
