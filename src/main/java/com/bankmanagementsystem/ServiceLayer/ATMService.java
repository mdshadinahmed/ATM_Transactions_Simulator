package com.bankmanagementsystem.ServiceLayer;

import com.bankmanagementsystem.CustomException.AccontNotFoundException;
import com.bankmanagementsystem.CustomException.DuplicateAccountFound;
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
    public void deposit() {

    }

    @Override
    public void withdraw() {

    }

    @Override
    public void transferMoney() {

    }
}
