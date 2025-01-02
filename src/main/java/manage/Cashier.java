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

    public Cashier(String name, String id, String accountNumber, String customer, double balance, int transactionsHandled, double totalSales) {
        super(name, id, accountNumber, customer, balance);
        this.transactionsHandled = transactionsHandled;
        this.totalSales = totalSales;
    }

    public void processTransaction(double saleAmount) {
        totalSales += saleAmount;
        transactionsHandled++;
        System.out.println("Transaction processed. Sale amount: " + saleAmount);
        System.out.println("Total sales: " + totalSales + ", Transactions handled: " + transactionsHandled);
    }

    public void showPerformance() {
        System.out.println("Cashier Performance:");
        System.out.println("Name: " + getName());
        System.out.println("Transactions Handled: " + transactionsHandled);
        System.out.println("Total Sales: " + totalSales);
    }

    public int getTransactionsHandled() {
        return transactionsHandled;
    }

    public double getTotalSales() {
        return totalSales;
    }
}
