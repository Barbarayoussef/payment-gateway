package com.bank.payment_gateway.service;

import com.bank.payment_gateway.model.Card;
import com.bank.payment_gateway.model.PaymentRequest;
import com.bank.payment_gateway.model.Transaction;
import com.bank.payment_gateway.repository.CardRepository;
import com.bank.payment_gateway.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public String processPayment(PaymentRequest paymentRequest) {
        Card card =cardRepository.findById(paymentRequest.getCardNumber()).orElse(null);
        Transaction transaction = new Transaction();
        transaction.setCardNumber(paymentRequest.getCardNumber());
        transaction.setAmount(paymentRequest.getAmount());

        if(card==null){
            transaction.setStatus("FAILED");
            transaction.setMessage("Card number is incorrect.");
            transactionRepository.save(transaction);
            return "Payment not succeed,Card Not Found";

        }
        if(card.getBalance()<paymentRequest.getAmount()){
            transaction.setStatus("FAILED");
            transaction.setMessage("Insufficient balance.");
            transactionRepository.save(transaction);
            return "Payment not succeed, the card balance is less than the amount";
        }
        card.setBalance(card.getBalance()-paymentRequest.getAmount());
        cardRepository.save(card);
        transaction.setStatus("SUCCESS");
        transaction.setMessage("Payment processed successfully.");
        transactionRepository.save(transaction);
        return "Payment succeeded! Deducted: "+ paymentRequest.getAmount() +" successfully. The remaining balance is "+ card.getBalance();
    }

    public String addNewCard(Card card){
        if(cardRepository.existsById(card.getCardNumber())){
            return "Failed: Card already exists";
        }
        cardRepository.save(card);
        return "Success: New card added successfully!";
    }
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public Card getCardDetails(String cardNumber) {
        return cardRepository.findById(cardNumber).orElse(null);
    }

}
