/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_bank_sytem;

/**
 *
 * @author hafizh
 */
public abstract class CustomerAccount {
    private String accountName;
    private String accountID;
    protected double balance;

    public CustomerAccount(String accountName, String accountID, double balance) {
        this.accountName = accountName;
        this.accountID = accountID;
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
}
