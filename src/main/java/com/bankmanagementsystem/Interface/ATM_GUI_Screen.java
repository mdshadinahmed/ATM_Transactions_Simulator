package com.bankmanagementsystem.Interface;

import com.bankmanagementsystem.Model.Account;

public interface ATM_GUI_Screen {
    public static final String bankName = "Dhaka Bank";
    public void createAccount(Account account);
    public void seeAllAccount();
    public void checkBalance(int accountID);
    public void deposit(int id , double deposit) ;
    public void withdraw(int id, double withdrawAmount);
    public void transferMoney(int senderAccountId, int receiverAccountID, double amount);
}
