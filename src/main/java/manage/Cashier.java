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
    private double totalSales;
    private double salary;
    private double penalty;
    private boolean isSalaryWithdrawn;

    public Cashier(String name, String id, String accountNumber, String customer, double balance, int transactionsHandled, double totalSales) {
        super(name, id, accountNumber, customer, balance);
        this.transactionsHandled = transactionsHandled;
        this.totalSales = totalSales;
        this.salary = salary;
        this.penalty = 0.0;
        this.isSalaryWithdrawn = false;
    }

    public void processTransaction(double saleAmount) {
        totalSales += saleAmount;
        transactionsHandled++;
        System.out.println("Transaction processed. Sale amount: " + saleAmount);
        System.out.println("Total sales: " + totalSales + ", Transactions handled: " + transactionsHandled);
    }

    public int getTransactionsHandled() {
        return transactionsHandled;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getSalary() {
        return salary;
    }

    public double getPenalty() {
        return penalty;
    }

    public double getTotalSalary() {
        return salary - penalty;
    }

    public void addPenalty(double amount) {
        if (amount > 0) {
            penalty += amount;
            System.out.println("Penalty of " + amount + " added. Total penalty: " + penalty);
        } else {
            System.out.println("Invalid penalty amount!");
        }
    }

    public void withdrawSalary() {
        if (!isSalaryWithdrawn) {
            double totalSalary = getTotalSalary();
            if (totalSalary > 0) {
                isSalaryWithdrawn = true;
                System.out.println("Salary of " + totalSalary + " withdrawn successfully.");
            } else {
                System.out.println("No salary available to withdraw.");
            }
        } else {
            System.out.println("Salary has already been withdrawn this month.");
        }
    }

    public void resetSalaryStatus() {
        isSalaryWithdrawn = false;
        penalty = 0.0;
        System.out.println("Salary status reset for the new month.");
    }

    void withdraw(double cashierWage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
