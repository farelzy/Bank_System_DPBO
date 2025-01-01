/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;

import com.mycompany.tubes_bank_sytem.CustomerAccount;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author farelzy
 */
abstract class Transaction {
    protected String transactionId;
    protected CustomerAccount customerAccount;
    protected double balance;
    private double amount;

    public Transaction(CustomerAccount customerAccount, double balance) {
        this.transactionId = generateTransactionId();
        this.customerAccount = customerAccount;
        this.balance = balance;
    }

    private String generateTransactionId() {
        return "TXN" + ThreadLocalRandom.current().nextInt(1000, 9999);
    }

    public abstract void process(double amount);


    public double getAmount() {
        return amount;
    }
}
