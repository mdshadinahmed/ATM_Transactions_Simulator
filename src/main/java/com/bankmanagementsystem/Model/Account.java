package com.bankmanagementsystem.Model;

import com.bankmanagementsystem.CustomException.NegativeAmountCannotBeDeposit;
import com.bankmanagementsystem.CustomException.NegativeNumbercannotBeAccountID;

import static java.lang.System.out;

public class Account {

    public static int OBJECT_COUNT = 0;
    {
        OBJECT_COUNT++;
        out.println("New Accounts Created");

    }

    private int accountHolderId;
    private String accountHolderName;
    private String accountType;
    private double firstDepositBalance;

    // No-Args Constructor to reduce Constructor Mismatch Exception
    public Account(){
        super();
    }

    // Constructor overloaded
    public Account(int id, String name){
        this(id,name,"null",0.0);
    }

    public Account(int accountHolderId, String accountHolderName,
                   String accountType, double firstDepositBalance) throws NegativeAmountCannotBeDeposit, NegativeNumbercannotBeAccountID {
        super();

       if(accountHolderId<=0  ){
           throw new NegativeNumbercannotBeAccountID("Id cannot not be negative or Zero");
       }else {
           this.accountHolderId = accountHolderId;
       }
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;

        if (firstDepositBalance<0){
            throw new NegativeAmountCannotBeDeposit("Negative Number cannot be deposit...");
        }else {
            this.firstDepositBalance = firstDepositBalance;
        }


    }



    //---------------------
    // getter setter


    public int getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(int accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getFirstDepositBalance() {
        return firstDepositBalance;
    }

    public void setFirstDepositBalance(double firstDepositBalance) {
        this.firstDepositBalance = this.firstDepositBalance + firstDepositBalance;
    }


    //-------------------------
    // toString

    @Override
    public String toString() {
        return
                "Account Holder Id : " + accountHolderId +"\n"+
                "Account Holder Name : '" + accountHolderName + "\n" +
                "Account Type : '" + accountType + "\n" +
                "Total Balance : " + firstDepositBalance ;
    }
}
