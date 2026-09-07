package com.bankmanagementsystem.ServiceLayer;

import com.bankmanagementsystem.CustomException.*;
import com.bankmanagementsystem.Model.Account;
import com.bankmanagementsystem.Interface.ATM_GUI_Screen;

import java.util.ArrayList;

public class ATMService implements ATM_GUI_Screen {

    ArrayList<Account> accounts  = new ArrayList<>();

    @Override
    public void createAccount(Account account){

     // duplicate account find
        int getPresentId = account.getAccountHolderId();
        boolean dupAccountIsFound = false;
        for (Account account1 : accounts){
            if (account1.getAccountHolderId() == getPresentId){
                dupAccountIsFound = true;
                break;
            }
        }

        if (dupAccountIsFound){
            throw new DuplicateAccountFound("Account has already created...");
        }else {
            accounts.add(account);

        }

    }

    @Override
    public void seeAllAccount(){
      if (!accounts.isEmpty()) {
          System.out.println("======== All Accounts ===========\n");
          for (Account account : accounts) {
              System.out.println(account.toString());
          }
      }
      else {
              throw new AccontNotFoundException("No accounts Available!");
          }
      }





    @Override
    public void checkBalance(int accountId) {

        int isFound = 0;
        for (Account account : accounts){
            if (account.getAccountHolderId() == accountId){
                System.out.println("Account Balance : "+account.getFirstDepositBalance());
                isFound = 1;
                break;
            }
        }
        if (isFound == 0){
            throw new AccontNotFoundException("Account Not Available!");
        }
    }

    @Override
    public void deposit(int id , double deposit) throws NegativeAmountCannotBeDeposit {

        for (Account account : accounts){
            if(account.getAccountHolderId() == id) {
                if (deposit<0){
                    throw new NegativeAmountCannotBeDeposit("Negative Number Found!");
                }else {
                    account.setFirstDepositBalance(deposit);
                    System.out.println("Successfully Deposit : " + deposit);
                }

            }
        }
    }

    @Override
    public void withdraw(int accountID, double withdrawAmount ) {
        for (Account account : accounts){
            if (account.getAccountHolderId() == accountID){
                if (withdrawAmount<=account.getFirstDepositBalance() && withdrawAmount>=0){
                    double newBalance= account.getFirstDepositBalance() - withdrawAmount;
                    account.setFirstDepositBalance(newBalance);
                    System.out.println("Withdraw Successful");
                    break;
                }else {
                    throw new InsufficientFundException("Invalid withdraw please check your account..");
                }
            }
        }
    }

    @Override
    public void transferMoney(int senderAccountId, int receiverAccountID, double amount) throws AccontNotFoundException, NegativeNumberFoundException, InsufficientFundException {


        Account sender = null;
        Account receiver = null;

        for (Account account : accounts){
            if (account.getAccountHolderId() == senderAccountId){
                sender = account;
                sender.setFirstDepositBalance(sender.getFirstDepositBalance() - amount);
                break;
            }
        }

        for (Account account : accounts){
            if (account.getAccountHolderId() == receiverAccountID){
                receiver = account;
                receiver.setFirstDepositBalance(receiver.getFirstDepositBalance() + amount);
                break;
            }
        }


    }
}
