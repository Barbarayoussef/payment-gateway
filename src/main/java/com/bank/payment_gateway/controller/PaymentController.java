package com.bank.payment_gateway.controller;

import com.bank.payment_gateway.model.Card;
import com.bank.payment_gateway.model.PaymentRequest;
import com.bank.payment_gateway.model.Transaction;
import com.bank.payment_gateway.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public String makePayment(@Valid @RequestBody PaymentRequest paymentRequest){
        return paymentService.processPayment(paymentRequest);
    }

    @PostMapping("/addcard")
    public String addCard(@Valid @RequestBody Card card){
        return paymentService.addNewCard(card);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return paymentService.getAllTransactions();
    }
    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<Card> getCardByNumber(@PathVariable String cardNumber) {
        Card card = paymentService.getCardDetails(cardNumber);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(card);
    }

}
