/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_bank_sytem;
import Employee.EmployeeAccount;

/**
 *
 * @author hafizh
 */
public abstract class Cashier extends EmployeeAccount {
    private double moneyIssued; 
    private int mistakes;     

    public Cashier(String name, String id, String accountID, double balance) {
        super(name, id, accountID, balance);
        this.moneyIssued = 0.0;
        this.mistakes = 0;
    }
    
    public void withdrawPaycheck(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            moneyIssued += amount;
            System.out.println("Paycheck Withdrawn: " + amount);
        } else {
            System.out.println("Invalid or Insufficient Balance for Paycheck.");
        }
    }

    public String getPerformance() {
        if (mistakes == 0) {
            return "Excellent";
        } else if (mistakes <= 3) {
            return "Good";
        } else {
            return "Needs Improvement";
        }
    }

    public double viewTotalWage() {
        return moneyIssued;
    }

    public void recordMistake() {
        mistakes++;
        System.out.println("Mistake recorded. Total mistakes: " + mistakes);
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Cashier Account cannot accept deposits.");
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed except for paycheck.");
    }
}
