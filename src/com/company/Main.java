package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    ArrayList<Loan> loanList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main MainMethod = new Main();
        MainMethod.start();


    }

    public void start(){
        readCSVFiles();
        viewLists();
        //issueLoan();
        renewLoan();
        viewLists();

    }

    public void issueLoan(){
        int barcode = inputBarcode();
        String userID = inputUserID();


        LoanManager loanManager = new LoanManager(itemList,loanList,userList);
        loanManager.issueLoan(barcode,userID);
    }

    public void renewLoan(){
        int barcode = inputBarcode();
        String userID = inputUserID();

        LoanManager loanManager = new LoanManager(itemList,loanList,userList);
        loanManager.renewLoan(barcode,userID);
    }

    public int inputBarcode(){
        System.out.println("Please enter the barcode of the item");
        return Integer.parseInt(scanner.nextLine());
    }

    public String inputUserID(){
        System.out.println("Please enter the User ID");
        return scanner.nextLine();
    }

    public void readCSVFiles(){
        CSVReader reader = new CSVReader();
        loanList = reader.readLoansCSV();
        userList = reader.readUsersCSV();
        itemList = reader.readItemsCSV();
    }

    public void viewLists(){
        ViewList viewList = new ViewList();
        viewList.ViewList(loanList);
       // viewList.ViewList(userList);
       // viewList.ViewList(itemList);

    }


}
