/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;

/**
 *
 * @author easterPC
 */
public class Account {
    private int accountID;
    private int customerID;
    private String accountType;
    private double balance;
    
    public Account(int accountID, int customerID, String accountType, double balance){
        this.accountID=accountID;
        this.customerID=customerID;
        this.accountType=accountType;
        this.balance=balance;
    }
    
    public int getAccountID(){
        return accountID;
    }
    public int getCustomerID(){
        return customerID;
    }
    public String getAccountType(){
        return accountType;
    }
    public double getBalance(){
        return balance;
    }
    
     public void setAccountID(int accountID){
        this.accountID=accountID;
    }
    public void setCustomerID(int customerID){
        this.customerID=customerID;
    }
    public void setAccountType(String accountType){
        this.accountType=accountType;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
}
