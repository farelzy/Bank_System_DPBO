/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage;

import manage.Employee;

/**
 *
 * @author LEGION
 */
abstract class EmployeeAccount extends Employee {
    private String accountNumber;
    private String customer;
    private double balance;

    public EmployeeAccount(String name, String id, String accountNumber, String customer, double balance) {
        super(name, id);
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
    }

    public void paycheck(double wage) {
        balance += wage;
        System.out.println("Paycheck added. New balance: " + balance);
    }

    public void penalty(double amount) {
        balance -= amount;
        System.out.println("Penalty deducted. New balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}
