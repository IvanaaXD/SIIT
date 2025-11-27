package bank;

import exception.InsufficientFundsException;

public class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Account(String accountHolder, double initialBalance) {
        this.accountNumber = generateAccountNumber();
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in the account.");
        }
        balance -= amount;
    }

    private String generateAccountNumber() {
        // Simplified account number generation for demonstration purposes.
        return "ACCT-" + System.nanoTime();
    }
}
