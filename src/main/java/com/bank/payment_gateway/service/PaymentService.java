package com.bank.payment_gateway.service;

import com.bank.payment_gateway.model.Card;
import com.bank.payment_gateway.model.PaymentRequest;
import com.bank.payment_gateway.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private CardRepository cardRepository;

    public String processPayment(PaymentRequest paymentRequest) {
        Card card =cardRepository.findById(paymentRequest.getCardNumber()).orElse(null);

        if(card==null){
            return "Payment not succeed,Card Not Found";
        }
        if(card.getBalance()<paymentRequest.getAmount()){
            return "Payment not succeed, the card balance is less than the amount";
        }
        card.setBalance(card.getBalance()-paymentRequest.getAmount());
        cardRepository.save(card);
        return "Payment succeeded!, deducted "+ paymentRequest.getAmount() +" successfully. The remaining balance is "+ card.getBalance();
    }

}
