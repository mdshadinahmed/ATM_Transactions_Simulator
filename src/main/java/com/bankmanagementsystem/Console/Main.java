package com.bankmanagementsystem.Console;


import com.bankmanagementsystem.CustomException.*;
import com.bankmanagementsystem.Model.Account;
import com.bankmanagementsystem.ServiceLayer.ATMService;

import java.lang.runtime.SwitchBootstraps;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.*;


public class Main {

    public static int accountId;


    public static void main(String[] args) {


        //---------------------
        //= Scanner
        Scanner scanner = new Scanner(in);

        //= Service Layer Object
        ATMService atmService = new ATMService();

        while (true){
            out.println("====== ATM Transaction Simulator ======");
            out.println("1. Create Account");
            out.println("2. See All Accounts");
            out.println("3. Check Balance");
            out.println("4. Deposit Money");
            out.println("5. Withdraw Money");
            out.println("6. Transfer Money");
            out.println("7. Exit");

            out.println("Enter your choice");
            try{
                int choice = scanner.nextInt();
                if (choice > 7 || choice<=0){
                    out.println("Please Enter the Number with 1-5\n\n");
                }else {
                    switch (choice){

                        //-----------------------------------------------
                        //= Create Account
                        //-----------------------------------------------
                        case 1:
                            try{
                                out.println("Enter Account Id");
                                accountId = scanner.nextInt();;

                                //-----------------------
                                //= Buffer Clear
                                scanner.nextLine();

                                out.println("Enter Account Holder Name : ");
                                String accountHolderName = scanner.nextLine();

                                out.println("Enter Account type : ");
                                String accountType = scanner.nextLine();

                                out.println("Enter First Deposit Money : ");
                                double firstDepositMoney = scanner.nextDouble();

                                Account account = new Account(accountId, accountHolderName,
                                        accountType, firstDepositMoney);

                                atmService.createAccount(account);

                            }catch (NegativeAmountCannotBeDeposit e){
                                out.println("Error : "+e.getMessage());;
                            }catch (NegativeNumbercannotBeAccountID e){
                                out.println("Error : "+e.getMessage());;
                            }catch (DuplicateAccountFound e){
                                out.println("Error: "+e.getMessage());
                            }
                            break;

                        //-----------------------------------------------
                        //= See All Account
                        //-----------------------------------------------
                        case 2:
                            try{
                                atmService.seeAllAccount();
                            }catch (AccontNotFoundException e){
                                out.println("Error : "+ e.getMessage());
                            }
                            break;

                        //-----------------------------------------------
                        //= Check Balance
                        //-----------------------------------------------
                        case 3:
                            out.println("Enter account id : ");
                            accountId = scanner.nextInt();
                            try{
                                atmService.checkBalance(accountId);
                            }catch (AccontNotFoundException e){
                                out.println("Error : "+e.getMessage());
                            }
                            break;

                        //-----------------------------------------------
                        //= Deposit Account
                        //-----------------------------------------------
                        case 4:
                            out.println("Enter Your account ID : ");
                            accountId = scanner.nextInt();
                            out.println("Enter your deposit amount");
                            int depositAmount = scanner.nextInt();
                            try {
                                atmService.deposit(accountId, depositAmount);
                            }catch (NegativeAmountCannotBeDeposit e){
                                out.println(e.getMessage());
                            }catch (AccontNotFoundException e){
                                out.println("Error : "+ e.getClass());
                            }
                            break;

                        //-----------------------------------------------
                        //= Withdraw Account
                        //-----------------------------------------------
                        case 5:
                            out.println("Enter your account id : ");
                            accountId = scanner.nextInt();

                            scanner.nextLine();

                            out.println("Enter Withdraw  Amount : ");
                            double amount = scanner.nextDouble();

                            try{
                                atmService.withdraw(accountId,amount);
                            }catch (InsufficientFundException e){
                                out.println("Error : " + e.getMessage());
                            }
                            break;

                    }
                }



            }catch (InputMismatchException e){
                out.println("Invalid choice!!\nPlease Enter Valid Number : ");
            }
        }



    }
}