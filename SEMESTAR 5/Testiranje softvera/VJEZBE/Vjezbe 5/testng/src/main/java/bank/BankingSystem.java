package bank;


import exception.AccountNotFoundException;
import exception.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

public class BankingSystem {
    private Map<String, Account> accounts;

    public BankingSystem() {
        this.accounts = new HashMap<>();
    }

    public void createAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public double getAccountBalance(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException("Account not found.");
        }
        return accounts.get(accountNumber).getBalance();
    }

    public void transferFunds(String senderAccountNumber, String recipientAccountNumber, double amount) {
        if (!accounts.containsKey(senderAccountNumber) || !accounts.containsKey(recipientAccountNumber)) {
            throw new AccountNotFoundException("One or both accounts not found.");
        }

        Account sender = accounts.get(senderAccountNumber);
        Account recipient = accounts.get(recipientAccountNumber);

        try {
            sender.withdraw(amount);
            recipient.deposit(amount);
        } catch (InsufficientFundsException e) {
            throw new InsufficientFundsException("Insufficient funds in the sender's account.");
        }
    }
}
