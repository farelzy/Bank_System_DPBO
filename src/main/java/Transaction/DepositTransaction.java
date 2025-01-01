/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;
import Account.CustomerAccount;

/**
 *
 * @author farelzy
 */
public class DepositTransaction extends Transaction {
    public DepositTransaction(CustomerAccount customerAccount, double balance) {
        super(customerAccount, balance);
    }

    @Override
    public void process(double amount) {
        balance += amount;
        System.out.println("Deposit Successful: " + amount);
    }
}