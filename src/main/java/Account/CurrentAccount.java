/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

/**
 *
 * @author VICTUS
 */
public class CurrentAccount extends CustomerAccount {

    public CurrentAccount(String accountName, String accountID, double balance, double par1) {
        super(accountName, accountID, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit to Current Account: " + amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
