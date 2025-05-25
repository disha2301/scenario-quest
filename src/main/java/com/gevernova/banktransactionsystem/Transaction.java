package com.gevernova.banktransactionsystem;

public class Transaction {
    private final String type; // "deposit" or "withdrawal"
    private final double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
}
