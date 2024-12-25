/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;

/**
 *
 * @author ROG STRIX
 */
public abstract class Transaction {
    private String transactionId;
    private CustomerAccount account; //classCustomer
    private double amount;

    public Transaction(String transactionId, CustomerAccount account, double amount) {
        this.transactionId = transactionId;
        this.account = account;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public CustomerAccount getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void process();
}
