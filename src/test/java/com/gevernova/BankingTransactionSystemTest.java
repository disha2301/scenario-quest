package com.gevernova;

import com.gevernova.bankingtransactionsystem.CurrentAccount;
import com.gevernova.bankingtransactionsystem.InsufficientFundsException;
import com.gevernova.bankingtransactionsystem.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankingTransactionSystemTest {
    private SavingsAccount savings;
    private CurrentAccount current;

    @Before
    public void setup() {
        savings = new SavingsAccount("SAV123");
        current = new CurrentAccount("CUR123", 500);
    }

    @Test
    public void testDeposit() {
        savings.deposit(1000);
        assertEquals(1000, savings.getBalance(), 0.001);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testSavingsWithdraw_InsufficientFunds() throws InsufficientFundsException {
        savings.deposit(100);
        savings.withdraw(200); // Should throw exception
    }

    @Test
    public void testCurrentWithdraw_WithinOverdraft() throws InsufficientFundsException {
        current.deposit(100);
        current.withdraw(500); // Allowed because overdraft is 500
        assertEquals(-400, current.getBalance(), 0.001);
    }

    @Test
    public void testTransactionSummary() throws InsufficientFundsException {
        savings.deposit(500);
        savings.withdraw(100);
        String summary = savings.generateTransactionSummary();
        assertTrue(summary.contains("Total Deposits: 500"));
        assertTrue(summary.contains("Total Withdrawals: 100"));
    }
}