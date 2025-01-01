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
import Transaction.DepositTransaction;
import Transaction.WithdrawTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<CustomerAccount> accounts = new ArrayList<>();

        SavingAccount savingAccount = new SavingAccount("John Doe", "SA001", 5000.0, 0.05);
        CurrentAccount currentAccount = new CurrentAccount("Jane Smith", "CA001", 10000.0, 0.02);

        accounts.add(savingAccount);
        accounts.add(currentAccount);

        System.out.println("Selamat datang di Sistem Perbankan!");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tampilkan Semua Akun");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nDaftar Akun:");
                    for (CustomerAccount account : accounts) {
                        System.out.println("Nama Akun: " + account.getAccountName() +
                                ", ID: " + account.getAccountID() +
                                ", Saldo: " + account.getBalance());
                    }
                    break;

                case 2:
                    System.out.print("\nMasukkan ID Akun: ");
                    String depositId = scanner.next();
                    CustomerAccount depositAccount = findAccountById(accounts, depositId);

                    if (depositAccount != null) {
                        System.out.print("Masukkan jumlah deposit: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);

                        DepositTransaction depositTransaction = new DepositTransaction(depositAccount, depositAmount);
                        depositTransaction.process(depositAmount);
                        System.out.println("Deposit berhasil!");
                    } else {
                        System.out.println("Akun tidak ditemukan!");
                    }
                    break;

                case 3:
                    System.out.print("\nMasukkan ID Akun: ");
                    String withdrawId = scanner.next();
                    CustomerAccount withdrawAccount = findAccountById(accounts, withdrawId);

                    if (withdrawAccount != null) {
                        System.out.print("Masukkan jumlah withdraw: ");
                        double withdrawAmount = scanner.nextDouble();

                        if (withdrawAccount.withdraw(withdrawAmount)) {
                            WithdrawTransaction withdrawTransaction = new WithdrawTransaction(withdrawAccount, withdrawAccount.getBalance());
                            withdrawTransaction.process(withdrawAmount);
                            System.out.println("Withdraw berhasil!");
                        } else {
                            System.out.println("Saldo tidak mencukupi!");
                        }
                    } else {
                        System.out.println("Akun tidak ditemukan!");
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan sistem perbankan!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
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


