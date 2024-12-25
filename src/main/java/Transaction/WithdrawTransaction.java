/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;

/**
 *
 * @author ROG STRIX
 */
class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(String transactionId, CustomerAccount account, double amount) {
        super(transactionId, account, amount);
    }

    @Override
    public void process() {
        if (getAccount().getBalance() >= getAmount()) {
            getAccount().setBalance(getAccount().getBalance() - getAmount());
            System.out.println("Withdrawal successful. Amount withdrawn: " + getAmount());
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }
}
