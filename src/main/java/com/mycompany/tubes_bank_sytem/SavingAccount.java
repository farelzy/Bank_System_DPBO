/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_bank_sytem;

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
        balance += amount;
        System.out.println("Deposit to Saving Account: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw from Saving Account: " + amount);
        } else {
            System.out.println("Insufficient Balance in Saving Account.");
        }
    }
}
