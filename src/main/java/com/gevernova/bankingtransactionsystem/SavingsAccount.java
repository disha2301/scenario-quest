package com.gevernova.bankingtransactionsystem;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if(amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if(getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in Savings Account.");
        }
        withdrawAmount(amount);

    }
}

