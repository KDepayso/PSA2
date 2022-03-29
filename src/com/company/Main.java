package com.company;

import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    ArrayList<Loan> loanList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    ParseInt parseInt = new ParseInt();

    public static void main(String[] args)  {
        Main MainMethod = new Main();
        MainMethod.start();

    }

    public void start(){
        readCSVFiles();
        menuLoop();

    }

    public void menuLoop(){
        displayOptionMenu();
        int input =  parseInt.parseInt();
        optionMenu(input);
    }

    public void optionMenu(int option)  {
        switch (option){
            case 1:viewLists("items");
            break;
            case 2:viewLists("loans");
            break;
            case 3:viewLists("users");
            break;
            case 4:issueLoan();
            break;
            case 5:renewLoan();
            break;
            case 6:returnLoan();
            break;
            case 7:
                writeLoansToCSV();
                System.out.println("Exiting program");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input, please try again");
                break;


        }
        System.out.println();
        System.out.println();
        menuLoop();

    }

    public void displayOptionMenu(){
        System.out.println("Enter 1 to view list of items" +
                "\nEnter 2 to view list of loans" +
                "\nEnter 3 to view list of users" +
                "\nEnter 4 to issue an item" +
                "\nEnter 5 to renew a loan" +
                "\nEnter 6 to return an item" +
                "\nEnter 7 to exit the program");
    }


    public void issueLoan(){
        int barcode = inputBarcode();
        String userID = inputUserID().toUpperCase();

        LoanManager loanManager = new LoanManager(itemList,loanList,userList);
        loanManager.issueLoan(barcode,userID);
    }

    public void renewLoan(){
        int barcode = inputBarcode();
        LoanManager loanManager = new LoanManager(itemList,loanList,userList);
        loanManager.renewLoan(barcode);
    }

    public void returnLoan(){
        int barcode = inputBarcode();
        LoanManager loanManager = new LoanManager(itemList,loanList,userList);
        loanManager.returnItem(barcode);

    }

    public int inputBarcode(){

        System.out.println("Please enter the barcode of the item");
        return parseInt.parseInt();
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

    public void viewLists(String list){
        ViewList viewList = new ViewList();
        switch (list){
            case "items": viewList.ViewList(itemList);
            break;
            case "loans": viewList.ViewList(loanList);
            break;
            case "users": viewList.ViewList(userList);
            break;
            default:
                break;
        }

    }

    public void writeLoansToCSV() {
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeLoanCSV(loanList);

    }


}
