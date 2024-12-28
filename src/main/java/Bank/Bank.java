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
import com.mycompany.tubes_bank_sytem.CustomerAccount;

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
    
    public void createAccount(CustomerAccount account){
        account.add(account);
        return 
    }
    
    public void closeAccount(CustomerAccount account){
        account.pop(account);
        System.out.println("Account under the name of " + account +" successfully deleted");
        
    }
    
    public void showAccountDetails(){
        
    }
}
