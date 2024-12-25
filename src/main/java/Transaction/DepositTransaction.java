/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;

/**
 *
 * @author ROG STRIX
 */
class DepositTransaction extends Transaction {
    public DepositTransaction(String transactionId, CustomerAccount account, double amount) {
        super(transactionId, account, amount);
    }

    @Override
    public void process() {
        getAccount().setBalance(getAccount().getBalance() + getAmount());
        System.out.println("Deposit successful. Amount deposited: " + getAmount());
    }
}
