package com.bankmanagementsystem.ServiceLayer;

import com.bankmanagementsystem.CustomException.*;
import com.bankmanagementsystem.Model.Account;
import com.bankmanagementsystem.Interface.ATM_GUI_Screen;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class ATMService implements ATM_GUI_Screen {

    ArrayList<Account> accounts  = new ArrayList<>();


    //---------------------------------------------------
    //= Create Account Function
    //---------------------------------------------------
    @Override
    public void createAccount(Account account){

     // Duplicate Account Find
        int getPresentId = account.getAccountHolderId();
        boolean dupAccountIsFound = false;
        for (Account account1 : accounts){
            if (account1.getAccountHolderId() == getPresentId){
                dupAccountIsFound = true;
                break;
            }
        }

        if (dupAccountIsFound){
            throw new DuplicateAccountFound("Account has already created...\n");
        }else {
            accounts.add(account);

        }

    }

    //---------------------------------------------------
    //= See All Account Function
    //---------------------------------------------------
    @Override
    public void seeAllAccount(){
      if (!accounts.isEmpty()) {
          System.out.println("======== All Accounts ===========\n");
          for (Account account : accounts) {
              System.out.println(account.toString());
          }
      }
      else {
              throw new AccontNotFoundException("No accounts Available!\n");
          }
      }


    //---------------------------------------------------
    //= Check Balance Function
    //---------------------------------------------------
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
            throw new AccontNotFoundException("Account Not Available!\n");
        }
    }

    //---------------------------------------------------
    //= Deposit Function
    //---------------------------------------------------
    @Override
    public void deposit(int id , double deposit) throws NegativeAmountCannotBeDeposit, InputMismatchException {

        Account accFound = null;

        for (Account account : accounts){
            if(account.getAccountHolderId() == id) {
                accFound.setFirstDepositBalance(accFound.getFirstDepositBalance()+deposit);
                accFound = account;
                break;
            }
        }

        if (accFound == null){
                throw new NegativeAmountCannotBeDeposit("Negative amount can't deposit");
            }

        }


    //---------------------------------------------------
    //= Withdraw Function
    //---------------------------------------------------
    @Override
    public void withdraw(int accountID, double withdrawAmount ) {
        for (Account account : accounts){
            if (account.getAccountHolderId() == accountID){
                if (withdrawAmount<=account.getFirstDepositBalance() && withdrawAmount>=0){
                    double newBalance= account.getFirstDepositBalance() - withdrawAmount;
                    account.setFirstDepositBalance(newBalance);
                    System.out.println("Withdraw Successful\n");
                    break;
                }else {
                    throw new InsufficientFundException("Invalid withdraw please check your account..\n");
                }
            }
        }
    }

    //---------------------------------------------------
    //= Transfer money Function
    //---------------------------------------------------
    @Override
    public void transferMoney(int senderAccountId, int receiverAccountID, double amount) throws AccontNotFoundException, NegativeNumberFoundException, InsufficientFundException {


        Account sender = null;
        Account receiver = null;

        for (Account account : accounts) {
            if (account.getAccountHolderId() == senderAccountId) {
                sender = account;
                break;
            }
        }
        if (sender == null){
            throw new AccontNotFoundException("Sender Account Not Found !");
        }


        for (Account account : accounts){
            if (account.getAccountHolderId() == receiverAccountID){
                receiver = account;
                break;
            }
        }
        if (receiver == null){
            throw new AccontNotFoundException("Receiver Account Not Found !");
        }


        // Transaction Here
        if (amount<=0){
            throw new NegativeNumberFoundException("Negative Number Cannot be deposit !");
        }
        if (amount>sender.getFirstDepositBalance()){
            throw new InsufficientFundException("Insufficient Fund ! ");
        }
        sender.setFirstDepositBalance
                    (sender.getFirstDepositBalance() - amount);
        receiver.setFirstDepositBalance
                    (receiver.getFirstDepositBalance() + amount);





    }
}
