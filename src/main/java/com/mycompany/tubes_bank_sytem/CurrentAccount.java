/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_bank_sytem;

/**
 *
 * @author VICTUS
 */
public class CurrentAccount extends CustomerAccount {
    public CurrentAccount(String accountName, String accountID, double balance) {
        super(accountName, accountID, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit to Current Account: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw from Current Account: " + amount);
        } else {
            System.out.println("Insufficient Balance in Current Account.");
        }
    }
}