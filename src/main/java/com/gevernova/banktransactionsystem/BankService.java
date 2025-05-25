package com.gevernova.banktransactionsystem;

import java.util.*;

public class BankService {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<String, List<Transaction>> transactionHistory = new HashMap<>();

    // Add account and initialize its transaction list
    public void addAccount(Account acc) {
        accounts.put(acc.getAccountNumber(), acc);
        transactionHistory.put(acc.getAccountNumber(), new ArrayList<>());
    }

    // Deposit amount and record transaction
    public void processDeposit(String accNum, double amount) {
        Account acc = accounts.get(accNum);
        if (acc == null) throw new IllegalArgumentException("Account not found");
        acc.deposit(amount);
        transactionHistory.get(accNum).add(new Transaction("deposit", amount));
    }

    // Withdraw amount and record transaction
    public void processWithdrawal(String accNum, double amount) {
        Account acc = accounts.get(accNum);
        if (acc == null) throw new IllegalArgumentException("Account not found");
        acc.withdraw(amount);
        transactionHistory.get(accNum).add(new Transaction("withdrawal", amount));
    }

    // Sum of all deposits for an account
    public double getTotalDeposits(String accNum) {
        return transactionHistory.getOrDefault(accNum, new ArrayList<>()).stream()
                .filter(t -> t.getType().equals("deposit"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Return all transactions for an account
    public List<Transaction> getTransactionHistory(String accNum) {
        return transactionHistory.getOrDefault(accNum, new ArrayList<>());
    }
}
