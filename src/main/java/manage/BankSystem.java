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
import manage.BankManager;

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

        Bank myBank = new Bank("Bank Jadi Apa");
        List<CustomerAccount> accounts = new ArrayList<>();
        SavingAccount savingAccount = new SavingAccount("John Doe", "SA001", 5000.0);
        CurrentAccount currentAccount = new CurrentAccount("Jane Smith", "CA001", 10000.0) {
        };
        LoanAccount loanAccount = new LoanAccount("Alice Borrower", "LA001", 0.0, 20000.0) {
        };

        accounts.add(savingAccount);
        accounts.add(currentAccount);
        accounts.add(loanAccount);

        BankManager bankManager = new BankManager("Mike the Manager", "MGR001", "LA001", "Bank Customer", 30000.0, 5000.0, 0);
        List<Cashier> cashierAccounts = new ArrayList<>();
        Cashier cashier = new Cashier("Sarah the Cashier", "CSR001", "SA001", "Bank Customer", 20000.0, 882, 0);
        Cashier cashier2 = new Cashier("Bill Da La Rosa", "CSR002", "CA001", "Bank Customer", 20000.0, 482, 2);
        Cashier cashier3 = new Cashier("Erwin Dasler", "CSR003", "LA001", "Bank Customer", 20000.0, 582, 0);
        
        cashierAccounts.add(cashier);
        cashierAccounts.add(cashier2);
        cashierAccounts.add(cashier3);
        
        System.out.println("+++++ Welcome to " + myBank.getBankName() + " Banking System! +++++");
        try {
            while (true) {
                System.out.println("\nSelect user type:");
                System.out.println("1. Log in as Customer");
                System.out.println("2. Log in as Employee");
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
                        employeeMenu(scanner, bankManager, cashierAccounts);
                        break;
                    case 3:
                        adminMenu(scanner, myBank, accounts, bankManager, cashierAccounts);
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
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Take Loan");
                System.out.println("4. Repay Loan");
                System.out.println("5. Return to Main Menu");
                System.out.print("Choose an option: ");

                int userType = -1;
                while (userType == -1) {
                    System.out.print("Choose an option: ");
                    try {
                        userType = scanner.nextInt();
                        if (userType < 1 || userType > 5) {
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

                    case 2:
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

                    case 3:
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

                    case 4:
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

                    case 5:
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void employeeMenu(Scanner scanner, BankManager bankManager, List<Cashier> cashierAccounts) {
    try {
        while (true) {
            System.out.println("\nEmployer Menu:");
            System.out.println("1. Show All Paychecks");
            System.out.println("2. Show All Penalties");
            System.out.println("3. Show Total Wage");
            System.out.println("4. Show Employee Performance");
            System.out.println("5. Withdraw Wage");
            System.out.println("6. Return to Main Menu");
            System.out.print("Choose an option: ");
            
            int choice = -1;
            while (choice == -1) {
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 6) {
                        System.out.println("Invalid option! Please choose a number between 1 and 6.");
                        choice = -1;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the buffer
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("\nPaycheck Details:");
                    System.out.println("Manager - Name: " + bankManager.getName() + ", Paycheck: " + bankManager.getBalance());
                    for (Cashier cashier : cashierAccounts) {
                        System.out.println("Cashier - Name: " + cashier.getName() + ", Paycheck: " + cashier.getBalance());
                    }
                    break;

                case 2:
                    System.out.println("\nPenalty Details:");
                    System.out.println("Manager - Name: " + bankManager.getName() + ", Mistakes Count: " + bankManager.getMistakeCount());
                    for (Cashier cashier : cashierAccounts) {
                        System.out.println("Cashier - Name: " + cashier.getName() + ", Mistake Count: " + cashier.getMistake());
                    }
                    break;

                case 3:
                    System.out.println("\nTotal Wage Balance:");
                    System.out.println("Bank Manager Wage Balance: " + bankManager.getBalance());
                    for (Cashier cashier : cashierAccounts) {
                        System.out.println("Cashier Wage Balance: " + cashier.getBalance());
                    }
                    break;

                case 4:
                    System.out.println("\nEmployee Performance:");
                    System.out.println("Manager - Name: " + bankManager.getName() + ", Loans Given: " + bankManager.getLoanGiven() +
                            ", Mistakes: " + bankManager.getMistakeCount());
                    for (Cashier cashier : cashierAccounts) {
                        System.out.println("Cashier - Name: " + cashier.getName() + ", Transactions: " + cashier.getTransactionsHandled() +
                            ", Mistakes: " + cashier.getMistake());
                    }
                    break;

                case 5:
                    System.out.println("\nWithdraw Wage:");
                    System.out.print("Enter Employee ID: ");
                    String employeeId = scanner.next();

                    if (employeeId.equals(bankManager.getId())) {
                        System.out.print("Enter Withdraw Amount for Manager: ");
                        double amount = scanner.nextDouble();
                        bankManager.withdrawPaycheck(amount);
                    } else {
                        Cashier targetCashier = null;
                        for (Cashier cashier : cashierAccounts) {
                            if (cashier.getId().equals(employeeId)) {
                                targetCashier = cashier;
                                break;
                            }
                        }

                        if (targetCashier != null) {
                            System.out.print("Enter Withdraw Amount for Cashier: ");
                            double amount = scanner.nextDouble();
                            if (amount <= targetCashier.getBalance()) {
                                targetCashier.paycheck(-amount);
                                System.out.println("Withdrawn paycheck for Cashier: " + targetCashier.getName() + ", Amount: " + amount);
                            } else {
                                System.out.println("Insufficient balance for Cashier.");
                            }
                        } else {
                            System.out.println("Invalid Employee ID!");
                        }
                    }
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    } catch (Exception e) {
        System.out.println("An error occurred in Employer Menu: " + e.getMessage());
    }
}

    private static void adminMenu(Scanner scanner, Bank myBank, List<CustomerAccount> accounts, BankManager bankManager, List<Cashier> cashierAccounts) {
        try {
            while (true) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Create Account");
                System.out.println("2. Close Account");
                System.out.println("3. Show All Customer Accounts");
                System.out.println("4. Show All Employee Accounts");
                System.out.println("5. Return to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\nSelect Account Type to Create:");
                        System.out.println("1. Current Account");
                        System.out.println("2. Saving Account");
                        System.out.println("3. Loan Account");

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
                                newAccount = new CurrentAccount(accountName, accountID, balance) {
                                };
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
                                newAccount = new LoanAccount(accountName, accountID, balance, loanLimit) {
                                };
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
                                accounts.remove(accountToClose); // Remove the account from the list
                                myBank.closeAccount(accountToClose); // Call the Bank's method to process closure
                                System.out.println("Account with ID " + accountIdToClose + " has been successfully removed.");
                            } else {
                                System.out.println("Account not found!");
                            }
                        break;

                    case 3:
                        System.out.println("\nCustomer Account List:");
                        for (CustomerAccount account : accounts) {
                            System.out.println("Account Name: " + account.getAccountName()
                                    + ", ID: " + account.getAccountID()
                                    + ", Balance: " + account.getBalance());
                        }
                        break;

                    case 4:
                        System.out.println("\nEmployee Account List:");
                        System.out.println("Bank Manager: " + bankManager.getName() + " - ID: " + bankManager.getId());
                        for (Cashier cashier : cashierAccounts) {
                            System.out.println("Cashier: " + cashier.getName() + " - ID: " + cashier.getId());
                        }
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid option!");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}
