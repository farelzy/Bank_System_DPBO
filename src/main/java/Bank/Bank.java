/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bank;

/**
 *
 * @author msi_k
 */ 

import java.util.ArrayList;
import Account.CustomerAccount;

public class Bank {
    private String bankName;
    private ArrayList<CustomerAccount> account;
    
    public Bank(String bankName){
        this.bankName = bankName;
        this.account = new ArrayList<>(); 
    }
    public String getBankName(){
        return bankName;
    }
    
    public void generateReport(){
        
    }
    
    public void createAccount(CustomerAccount Newaccount){//ganti parameter
        account.add(Newaccount);
    }
    
    public void closeAccount(CustomerAccount exisitingaccount){//ganti parameter dan pop to remove
        account.remove(exisitingaccount);
        System.out.println("Account under the name of " + account +" successfully deleted");
        
    }
    
    public void showAccountDetails(){
        
    }
}
