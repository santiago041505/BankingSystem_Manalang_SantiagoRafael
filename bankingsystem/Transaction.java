/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;

import java.sql.Timestamp;

/**
 *
 * @author easterPC
 */
public class Transaction {
    private int transactionID;
    private int accountID;
    private String transactionType;
    private double amount;
    private Timestamp transactionDate;

    public Transaction(int transactionID, int accountID, String transactionType, double amount, Timestamp transactionDate) {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
