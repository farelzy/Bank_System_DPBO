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
public class SavingAccount extends CustomerAccount {

    private double interestRate;

    public SavingAccount(String accountName, String accountID, double balance, double interestRate) {
        super(accountName, accountID, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Inital Amount: " + getBalance());
        balance += amount;
        System.out.println("Deposit to Saving Account: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }
}
