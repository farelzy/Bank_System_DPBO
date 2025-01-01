/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;
import com.mycompany.tubes_bank_sytem.CustomerAccount;

/**
 *
 * @author farelzy
 */
class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(CustomerAccount customerAccount, double balance) {
        super(customerAccount, balance);
    }

    @Override
    public void process(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal Successful: " + amount);
        } else {
            System.out.println("Insufficient Balance.");
        }
    }
}
