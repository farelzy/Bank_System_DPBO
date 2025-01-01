/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;
import Transaction.Transaction;
import Account.CustomerAccount;
/**
 *
 * @author farelzy
 */
public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(CustomerAccount customerAccount, double balance) {
        super(customerAccount, balance);
    }

    @Override
    public void process(double amount) {
        if (amount <= customerAccount.getBalance()) {
            customerAccount.withdraw(amount); // Panggil metode withdraw dari CustomerAccount
            System.out.println("Withdrawal Successful: " + amount);
            System.out.println("New Balance: " + customerAccount.getBalance());
        } else {
            System.out.println("Insufficient Balance.");
        }
    }
}
