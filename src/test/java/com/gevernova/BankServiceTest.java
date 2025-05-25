package com.gevernova;

import com.gevernova.banktransactionsystem.Account;
import com.gevernova.banktransactionsystem.BankService;
import com.gevernova.banktransactionsystem.CurrentAccount;
import com.gevernova.banktransactionsystem.SavingsAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BankServiceTest {

    @Test
    public void testPositiveDepositAndWithdrawal() {
        BankService bank = new BankService();
        Account acc = new SavingsAccount("1001", 1000);
        bank.addAccount(acc);

        bank.processDeposit("1001", 500);
        bank.processWithdrawal("1001", 200);

        assertEquals(1300, acc.getBalance(), 0.01);
        assertEquals(2, bank.getTransactionHistory("1001").size());
    }

    @Test
    public void testNegativeDepositThrowsException() {
        BankService bank = new BankService();
        Account acc = new CurrentAccount("1002", 1000);
        bank.addAccount(acc);

        assertThrows(IllegalArgumentException.class, () -> bank.processDeposit("1002", -100));
    }

    @Test
    public void testOverdraftThrowsException() {
        BankService bank = new BankService();
        Account acc = new SavingsAccount("1003", 500);
        bank.addAccount(acc);

        assertThrows(IllegalArgumentException.class, () -> bank.processWithdrawal("1003", 600));
    }

    @Test
    public void testInvalidAccountThrowsException() {
        BankService bank = new BankService();
        assertThrows(IllegalArgumentException.class, () -> bank.processDeposit("9999", 100));
    }

    @Test
    public void testTransactionSummary() {
        BankService bank = new BankService();
        Account acc = new SavingsAccount("1004", 1000);
        bank.addAccount(acc);

        bank.processDeposit("1004", 200);
        bank.processDeposit("1004", 300);
        bank.processWithdrawal("1004", 100);

        double totalDeposits = bank.getTotalDeposits("1004");
        assertEquals(500, totalDeposits, 0.01);
    }
}
