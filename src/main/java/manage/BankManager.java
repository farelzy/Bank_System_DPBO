/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage;

/**
 *
 * @author LEGION 
 */
class BankManager extends EmployeeAccount {
    private double loanGiven;
    private int mistake;

    public BankManager(String name, String id, String accountNumber, String customer, double balance, double loanGiven, int mistake) {
        super(name, id, accountNumber, customer, balance);
        this.loanGiven = loanGiven;
        this.mistake = mistake;
    }

    public void withdrawPaycheck(double amount) {
        if (amount <= getBalance()) {
            paycheck(-amount);
            System.out.println("Withdrawn paycheck: " + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void moneyLoaned(double amount) {
        loanGiven += amount;
        System.out.println("Loan given. Total loans: " + loanGiven);
    }

    public void viewTotalWage() {
        System.out.println("Total wage balance: " + getBalance());
    }
    
    public double getLoanGiven() {
        return loanGiven;
    }

    public int getMistakeCount() {
        return mistake;
    }
}
