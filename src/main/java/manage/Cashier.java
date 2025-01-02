/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage;
/**
 *
 * @author hafizh
 */


public class Cashier extends EmployeeAccount {
    private int transactionsHandled;
    private int mistake;

    public Cashier(String name, String id, String accountNumber, String customer, double balance, int transactionsHandled, int mistake) {
        super(name, id, accountNumber, customer, balance);
        this.transactionsHandled = transactionsHandled;
        this.mistake = mistake;
    }

    public void withdrawPaycheck(double amount) {
    if (amount <= getBalance()) {
        paycheck(-amount);
        System.out.println("Withdrawn paycheck: " + amount);
    }else {
        System.out.println("Insufficient balance.");
       }
    }
    
    public void viewTotalWage() {
        System.out.println("Total wage balance: " + getBalance());
    }
    
    public void showPerformance() {
        System.out.println("Cashier Performance:");
        System.out.println("Name: " + getName());
        System.out.println("Transactions Handled: " + transactionsHandled);
        System.out.println("Total Mistake: " + mistake);
    }

    public int getTransactionsHandled() {
        return transactionsHandled;
    }
    
    public int getMistake(){
        return mistake;
    }
}


