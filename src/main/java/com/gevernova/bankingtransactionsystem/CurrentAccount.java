package com.gevernova.bankingtransactionsystem;

import java.time.LocalDateTime;

// CurrentAccount allows overdraft up to a limit
public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, double overdraftLimit) {
        super(accountNumber);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if(amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if(getBalance() + overdraftLimit < amount) {
            throw new InsufficientFundsException("Exceeded overdraft limit in Current Account.");
        }
        withdrawAmount(amount);
    }
}
