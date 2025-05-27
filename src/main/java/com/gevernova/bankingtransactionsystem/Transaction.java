package com.gevernova.bankingtransactionsystem;

import java.time.LocalDateTime;

public class Transaction {
    private String type; // "Deposit" or "Withdrawal"
    private double amount;
    private LocalDateTime timestamp;

    // costructor
    public Transaction(String type, double amount, LocalDateTime timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // getters
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
