/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage;

/**
 *
 * @author ROG STRIX
 */
import Account.SavingAccount;
import Account.CurrentAccount;
import Account.CustomerAccount;
import Account.LoanAccount;
import Transaction.DepositTransaction;
import Transaction.WithdrawTransaction;
import Bank.Customer;
import Bank.Bank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<CustomerAccount> accounts = new ArrayList<>();
        Bank myBank = new Bank("myBank");
        SavingAccount savingAccount = new SavingAccount("John Doe", "SA001", 5000.0, 0.05);
        CurrentAccount currentAccount = new CurrentAccount("Jane Smith", "CA001", 10000.0, 0.02);
        LoanAccount loanAccount = new LoanAccount("Alice Brown", "LA001", 0.0, 20000.0);

        accounts.add(savingAccount);
        accounts.add(currentAccount);
        accounts.add(loanAccount);

        BankManager bankManager = new BankManager("Mike Manager", "MGR001", "BM001", "Bank Customer", 30000.0, 5000.0, 0);
        Cashier cashier = new Cashier("Sarah Cashier", "CSR001", "CS001", "Bank Customer", 20000.0, 0, 0.0);

        NotificationService notificationService = new NotificationService("Bank Notification Service");

        System.out.println("Welcome to " + myBank.getBankName() + " System!");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Login to Customer Account");
            System.out.println("2. Login to Employee Account");
            System.out.println("3. Withdraw");
            System.out.println("4. Take Loan");
            System.out.println("5. Repay Loan");
            System.out.println("6. View Bank Manager Details");
            System.out.println("7. Process Cashier Transaction");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAccount List:");
                    for (CustomerAccount account : accounts) {
                        System.out.println("Account Name: " + account.getAccountName() +
                                ", ID: " + account.getAccountID() +
                                ", Balance: " + account.getBalance());
                    }
                    break;

                case 2:
                    System.out.print("\nEnter Account ID: ");
                    String depositId = scanner.next();
                    CustomerAccount depositAccount = findAccountById(accounts, depositId);

                    if (depositAccount != null) {
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);

                        DepositTransaction depositTransaction = new DepositTransaction(depositAccount, depositAccount.getBalance());
                        depositTransaction.process(depositAmount);
                        notificationService.sendNotification(new Customer(depositAccount.getAccountName(), depositAccount.getAccountID()),
                                "Deposit of " + depositAmount + " successful.");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter Account ID: ");
                    String withdrawId = scanner.next();
                    CustomerAccount withdrawAccount = findAccountById(accounts, withdrawId);

                    if (withdrawAccount != null) {
                        System.out.print("Enter Withdraw Amount: ");
                        double withdrawAmount = scanner.nextDouble();

                        if (withdrawAccount.withdraw(withdrawAmount)) {
                            WithdrawTransaction withdrawTransaction = new WithdrawTransaction(withdrawAccount, withdrawAccount.getBalance());
                            withdrawTransaction.process(withdrawAmount);
                            notificationService.sendNotification(new Customer(withdrawAccount.getAccountName(), withdrawAccount.getAccountID()),
                                    "Withdrawal of " + withdrawAmount + " successful.");
                        } else {
                            System.out.println("Insufficient balance!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter Loan Account ID: ");
                    String loanId = scanner.next();
                    CustomerAccount loanAccountToUse = findAccountById(accounts, loanId);

                    if (loanAccountToUse instanceof LoanAccount) {
                        System.out.print("Enter Loan Amount: ");
                        double loanAmount = scanner.nextDouble();
                        ((LoanAccount) loanAccountToUse).takeLoan(loanAmount);
                    } else {
                        System.out.println("Account not eligible for loans!");
                    }
                    break;

                case 5:
                    System.out.print("\nEnter Loan Account ID: ");
                    String repayId = scanner.next();
                    CustomerAccount repayAccount = findAccountById(accounts, repayId);

                    if (repayAccount instanceof LoanAccount) {
                        System.out.print("Enter Repayment Amount: ");
                        double repayAmount = scanner.nextDouble();
                        ((LoanAccount) repayAccount).returnLoan(repayAmount);
                    } else {
                        System.out.println("Account not eligible for loan repayment!");
                    }
                    break;

                case 6:
                    System.out.println("\nBank Manager Details:");
                    System.out.println("Name: " + bankManager.getName());
                    System.out.println("ID: " + bankManager.getId());
                    System.out.println("Balance: " + bankManager.getBalance());
                    System.out.println("Loans Given: " + bankManager.getLoanGiven());
                    System.out.println("Mistakes Count: " + bankManager.getMistakeCount());
                    break;

                case 7:
                    System.out.print("\nEnter Sale Amount: ");
                    double saleAmount = scanner.nextDouble();
                    cashier.processTransaction(saleAmount);
                    break;

                case 8:
                    System.out.println("Thank you for using the Bank System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static CustomerAccount findAccountById(List<CustomerAccount> accounts, String accountId) {
        for (CustomerAccount account : accounts) {
            if (account.getAccountID().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
}


