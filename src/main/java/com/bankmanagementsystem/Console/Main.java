package com.bankmanagementsystem.Console;


import com.bankmanagementsystem.CustomException.AccontNotFoundException;
import com.bankmanagementsystem.CustomException.DuplicateAccountFound;
import com.bankmanagementsystem.CustomException.NegativeNumberCannotBeDeposit;
import com.bankmanagementsystem.CustomException.NegativeNumbercannotBeAccountID;
import com.bankmanagementsystem.Model.Account;
import com.bankmanagementsystem.ServiceLayer.ATMService;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.*;


public class Main {


    public static void main(String[] args) {


        //---------------------
        //= Scanner
        Scanner scanner = new Scanner(in);

        //= Service Layer
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
                        case 1:
                            try{
                                out.println("Enter Account Id");
                                int  accountId = scanner.nextInt();;

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

                            }catch (NegativeNumberCannotBeDeposit e){
                                out.println("Error : "+e.getMessage());;
                            }catch (NegativeNumbercannotBeAccountID e){
                                out.println("Error : "+e.getMessage());;
                            }catch (DuplicateAccountFound e){
                                out.println("Error: "+e.getMessage());
                            }
                            break;
                        case 2:
                            try{
                                atmService.seeAllAccount();
                            }catch (AccontNotFoundException e){
                                out.println("Errors : "+ e.getMessage());
                            }
                            break;
                        case 3:
                            out.println("Enter account id : ");
                            int accountId = scanner.nextInt();
                            try{
                                atmService.checkBalance(101);
                            }catch (AccontNotFoundException e){
                                out.println("Error : "+e.getMessage());
                            }

                    }
                }



            }catch (InputMismatchException e){
                out.println("Invalid choice!!\nPlease Enter Valid Number : ");
                break;
            }
        }



    }
}