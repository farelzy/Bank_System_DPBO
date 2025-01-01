/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

import Account.CustomerAccount;

/**
 *
 * @author VICTUS
 */
public class LoanAccount extends CustomerAccount {

    private double loanLimit;

    public LoanAccount(String accountName, String accountID, double balance, double loanLimit) {
        super(accountName, accountID, balance);
        this.loanLimit = loanLimit;
    }

    public void takeLoan(double amount) {
        if (amount <= loanLimit) {
            balance += amount;
            System.out.println("Loan Approved: " + amount);
        } else {
            System.out.println("Loan Amount Exceeds Limit.");
        }
    }

    public void returnLoan(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Loan Repaid: " + amount);
        } else {
            System.out.println("Not Enough Balance to Repay Loan.");
        }
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Deposit not applicable for Loan Account.");
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
