package com.bank.payment_gateway.controller;

import com.bank.payment_gateway.model.Card;
import com.bank.payment_gateway.model.PaymentRequest;
import com.bank.payment_gateway.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public String makePayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.processPayment(paymentRequest);
    }

    @PostMapping("/addcard")
    public String addCard(@RequestBody Card card){
        return paymentService.addNewCard(card);
    }

}
