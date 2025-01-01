/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Bank.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ROG STRIX
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
