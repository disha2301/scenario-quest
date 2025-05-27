package com.gevernova.bankingtransactionsystem;

import java.time.LocalDateTime;
import java.util.*;

// base for all account types
abstract class Account {
    private String accountNumber;
    private double balance;

    // Static Map to keep list of transactions for all accounts keyed by account number
    private static Map<String, List<Transaction>> transactionMap = new HashMap<>();

    public Account(String accountNumber){
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        // Initialize transactions list for this account if absent
        transactionMap.putIfAbsent(accountNumber, new ArrayList<>());
    }

    // getters
    public String getAccountNumber(){ return accountNumber; }
    public double getBalance(){ return balance; }

    // Deposit method - common to all accounts
    public void deposit(double amount){
        if(amount <= 0) throw new IllegalArgumentException("Please enter a positive amount");
        balance += amount;
        recordTransaction(new Transaction("Deposit", amount, LocalDateTime.now()));
    }

    // Abstract withdraw method to enforce subclass implementation (LSP)
    public abstract void withdraw(double amount) throws InsufficientFundsException;

    // Protected helper method to safely reduce balance and record withdrawal
    protected void withdrawAmount(double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        // No balance check here
        balance -= amount;
        recordTransaction(new Transaction("Withdrawal", amount, LocalDateTime.now()));
    }

    // Record transactions in the map
    protected void recordTransaction(Transaction txn) {
        List<Transaction> txns = transactionMap.get(accountNumber);
        if(txns == null){
            txns = new ArrayList<>();
            transactionMap.put(accountNumber, txns);
        }
        txns.add(txn);
    }

    // Get all transactions for this account
    public List<Transaction> getTransactions(){
        return Collections.unmodifiableList(transactionMap.get(accountNumber));
    }

    // generating summary using java8 features
    public String generateTransactionSummary(){
        List<Transaction> txns = transactionMap.get(accountNumber);
        if(txns == null || txns.isEmpty()){
            return String.format("Account: %s\nBalance: %.2f\nNo transactions found.\n", accountNumber, balance);
        }

        // get total deposits
        double totalDepo = txns.stream()
                .filter(t->"Deposit".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        // get total withdrawals
        double totalWithdraw = txns.stream()
                .filter(t->"Withdrawal".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return String.format(
                "Account: %s\nBalance: %.2f\nTotal Deposits: %.2f\nTotal Withdrawals: %.2f\n",
                accountNumber, balance, totalDepo, totalWithdraw);
    }
}
