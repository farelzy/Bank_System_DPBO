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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class BankSystem {
    private static CustomerAccount findAccountById(List<CustomerAccount> accounts, String accountId) {
        for (CustomerAccount account : accounts) {
            if (account.getAccountID().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
    
    public static String generateAccountID(String prefix) {
        Random random = new Random();
        int randomNumber = 20 + random.nextInt(980); 
        return prefix + randomNumber;
    }
    
    public static String percentageInterestRate(double interestRate) {
        return String.format("%.1f%%", interestRate * 100);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Bank myBank = new Bank("myBank");
        List<CustomerAccount> accounts = new ArrayList<>();
        SavingAccount savingAccount = new SavingAccount("John Doe", "SA001", 5000.0);
        CurrentAccount currentAccount = new CurrentAccount("Jane Smith", "CA001", 10000.0) {};
        LoanAccount loanAccount = new LoanAccount("Alice Borrower", "LA001", 0.0, 20000.0) {};
 
        accounts.add(savingAccount);
        accounts.add(currentAccount);
        accounts.add(loanAccount);

        BankManager bankManager = new BankManager("Mike the Manager", "MGR001", "BM001", "Bank Customer", 30000.0, 5000.0, 0);
        Cashier cashier = new Cashier("Sarah the Cashier", "CSR001", "CS001", "Bank Customer", 20000.0, 0, 0.0);

        System.out.println("Welcome to " + myBank.getBankName() + " Banking System!");
        try {
            while (true) {
                System.out.println("\nSelect user type:");
                System.out.println("1. Log in as Customer");
                System.out.println("2. Log in as Employer");
                System.out.println("3. Log in as Admin");
                System.out.println("4. Exit");
                int userType = -1;
                while (userType == -1) {
                    System.out.print("Choose an option: ");
                    try {
                        userType = scanner.nextInt();
                        if (userType < 1 || userType > 4) {
                            System.out.println("Invalid option! Please choose a number between 1 and 4.");
                            userType = -1;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine();
                    }
                }

                switch (userType) {
                    case 1: 
                        customerMenu(scanner, accounts);
                        break;

                    case 2:
                        employerMenu(scanner, bankManager, cashier);
                        break;
                    case 3:
                        adminMenu(scanner, myBank, accounts);
                        break;
                    case 4:
                        System.out.println("Thank you for using the banking system!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option!");
                }
            } 
        } catch (Exception e) {
                System.out.println("invalid input");
        }
    }
    

    private static void customerMenu(Scanner scanner, List<CustomerAccount> accounts) {
        try {
        while (true) {
            NotificationService notificationService = new NotificationService("Bank Notification");
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Take Loan");
            System.out.println("5. Repay Loan");
            System.out.println("6. Return to Main Menu");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int userType = -1;
                while (userType == -1) {
                    System.out.print("Choose an option: ");
                    try {
                        userType = scanner.nextInt();
                        if (userType < 1 || userType > 7) {
                            System.out.println("Invalid option! Please choose a number between 1 and 7.");
                            userType = -1;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine();
                    }
                }
            
            switch (userType) {
                case 1:
                    System.out.println("\nAccount List:");
                    for (CustomerAccount account : accounts) {
                        System.out.println("Account Name: " + account.getAccountName() +
                                ", ID: " + account.getAccountID() +
                                ", Balance: " + account.getBalance() +
                                ", Interest Rate: " + percentageInterestRate(account.getInterestRate()));
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

                        if (withdrawAccount.getBalance() >= withdrawAmount) {
                            WithdrawTransaction withdrawTransaction = new WithdrawTransaction(withdrawAccount, withdrawAccount.getBalance());
                            withdrawTransaction.process(withdrawAmount);
                            notificationService.sendNotification(new Customer(withdrawAccount.getAccountName(), withdrawAccount.getAccountID()),
                                    "Withdrawal of " + withdrawAmount + " successful.");
                        } else {
                            notificationService.sendNotification(new Customer(withdrawAccount.getAccountName(), withdrawAccount.getAccountID()),
                                    "Insufficient funds for withdrawal of " + withdrawAmount + ".");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter Account ID: ");
                    String loanId = scanner.next();
                    CustomerAccount loanAccount = findAccountById(accounts, loanId);

                    if (loanAccount instanceof LoanAccount) {
                        System.out.print("Enter loan amount: ");
                        double loanAmount = scanner.nextDouble();
                        ((LoanAccount) loanAccount).takeLoan(loanAmount);
                    } else {
                        System.out.println("Invalid account for loan!");
                    }
                    break;

                case 5:
                    System.out.print("\nEnter Account ID: ");
                    String repayId = scanner.next();
                    CustomerAccount repayAccount = findAccountById(accounts, repayId);

                    if (repayAccount instanceof LoanAccount) {
                        System.out.print("Enter repayment amount: ");
                        double repayAmount = scanner.nextDouble();
                        ((LoanAccount) repayAccount).returnLoan(repayAmount);
                    } else {
                        System.out.println("Invalid account for loan repayment!");
                    }
                    break;

                case 6:
                    return;
                case 7: 
                    System.out.println("Thank you for using the banking system!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid option!");
                }
            }
            } catch (Exception e) {
            System.out.println("invalid input");
        }
    }

    private static void employerMenu(Scanner scanner, BankManager bankManager, Cashier cashier) {
        try {
        while (true) {
            System.out.println("\nEmployer Menu:");
            System.out.println("1. View Bank Manager Details");
            System.out.println("2. View Cashier Details");
            System.out.println("3. Process Transaction by Cashier");
            System.out.println("4. Return to Main Menu");
            System.out.print("Choose an option: ");
            
            int userType = -1;
                while (userType == -1) {
                    System.out.print("Choose an option: ");
                    try {
                        userType = scanner.nextInt();
                        if (userType < 1 || userType > 4) {
                            System.out.println("Invalid option! Please choose a number between 1 and 4.");
                            userType = -1;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine();
                    }
                }
            

            switch (userType) {
                case 1:
                    System.out.println("\nBank Manager Details:");
                    System.out.println("Name: " + bankManager.getName());
                    System.out.println("ID: " + bankManager.getId());
                    System.out.println("Balance: " + bankManager.getBalance());
                    System.out.println("Loans Given: " + bankManager.getLoanGiven());
                    System.out.println("Mistakes Count: " + bankManager.getMistakeCount());
                    break;

                case 2:
                    System.out.println("\nCashier Details:");
                    System.out.println("Name: " + cashier.getName());
                    System.out.println("ID: " + cashier.getId());
                    System.out.println("Balance: " + cashier.getBalance());
                    System.out.println("Total Sales: " + cashier.getTotalSales());
                    System.out.println("Transactions Handled: " + cashier.getTransactionsHandled());
                    break;

                case 3:
                    System.out.print("\nEnter Sale Amount: ");
                    double saleAmount = scanner.nextDouble();
                    cashier.processTransaction(saleAmount);
                    break;
                    
                case 4:
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }
    
    private static void adminMenu(Scanner scanner, Bank myBank, List<CustomerAccount> accounts) {
        try {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Close Account");
            System.out.println("3. Show All Customer Accounts");
            System.out.println("4. Return to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
           

            switch (choice) {
                case 1:
                    System.out.println("\nSelect Account Type to Create:");
                    System.out.println("1. Current Account");
                    System.out.println("2. Saving Account");
                    System.out.println("3. Loan Account");
                    System.out.print("Choose an option: ");
                    
                    int userType = -1;
                    while (userType == -1) {
                        System.out.print("Choose an option: ");
                        try {
                            userType = scanner.nextInt();
                            if (userType < 1 || userType > 4) {
                                System.out.println("Invalid option! Please choose a number between 1 and 4.");
                                userType = -1;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input! Please enter a number.");
                            scanner.nextLine();
                        }
                    }

                    System.out.print("Enter Account Name: ");
                    String accountName = scanner.next();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();

                    CustomerAccount newAccount = null;
                    switch (userType) {
                        case 1:
                            String accountID = generateAccountID("CA");
                            System.out.println("Generated Account ID: " + accountID);
                            newAccount = new CurrentAccount(accountName, accountID, balance) {};
                            accounts.add(newAccount);
                            break;
                        case 2:
                            accountID = generateAccountID("SA");
                            System.out.println("Generated Account ID: " + accountID);             
                            newAccount = new SavingAccount(accountName, accountID, balance);
                            accounts.add(newAccount);
                            break;

                        case 3:
                            accountID = generateAccountID("LA");
                            System.out.println("Generated Account ID: " + accountID);
                            System.out.print("Enter Loan Limit: ");
                            double loanLimit = scanner.nextDouble();
                            newAccount = new LoanAccount(accountName, accountID, balance, loanLimit) {};
                            accounts.add(newAccount);
                            break;
                        default:
                            System.out.println("Invalid option!");
                            continue;
                    }

                    if (newAccount != null) {
                        myBank.createAccount(newAccount);  // Create the account using Bank's method
                    }
                    break;

                case 2:
                    System.out.print("\nEnter Account ID to Close: ");
                    String accountIdToClose = scanner.next();
                    CustomerAccount accountToClose = findAccountById(accounts, accountIdToClose);

                    if (accountToClose != null) {
                        myBank.closeAccount(accountToClose);  // Close the account using Bank's method
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.println("\nAccount List:");
                    for (CustomerAccount account : accounts) {
                        System.out.println("Account Name: " + account.getAccountName() +
                                ", ID: " + account.getAccountID() +
                                ", Balance: " + account.getBalance());
                    }
                    break;
                    
                case 4:
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }
}