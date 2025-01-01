/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

/**
 *
 * @author hafizh
 */
public abstract class CustomerAccount {
    protected String accountName;
    protected String accountID;
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

    public abstract void deposit(double amount);

    public abstract boolean withdraw(double amount);
}
