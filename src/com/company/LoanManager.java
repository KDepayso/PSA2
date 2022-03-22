package com.company;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;


public class LoanManager {

    private ArrayList<Item> items;
    private ArrayList<Loan> loans;
    private ArrayList<User> users;

    Scanner scanner = new Scanner(System.in);

    public LoanManager(ArrayList<Item> items, ArrayList<Loan> loans, ArrayList<User> users) {
        this.items = items;
        this.loans = loans;
        this.users = users;

    }

    public void issueLoan(int barcode, String userID){

        while(true){
            try{
                if(barcodeDoesNotExist(barcode))
                    throw new BarCodeNotFoundException("BarCode doesn't exist");

                if(userIDDoesNotExist(userID))
                    System.out.println("UserID doesn't exist");
                if(itemAlreadyLoaned(barcode))
                    System.out.println("Item already loaned");

                break;

            }
            catch (BarCodeNotFoundException ex){
                System.out.println("BarCode does not exist, please try again");
                barcode = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception ex){
                System.out.println("There was an error with your input, please try again");
            }
        }
        LocalDate issueDate = getDueDate(barcode);



        Loan newLoan = new Loan(barcode,userID.toUpperCase(Locale.ROOT),issueDate);

        loans.add((newLoan));
    }

    public void renewLoan(int barcode, String userID){
        if(barcodeDoesNotExist(barcode))
            System.out.println("BarCode doesn't exist");
        if(userIDDoesNotExist(userID))
            System.out.println("UserID doesn't exist");

        if(exceedMaxRenews(barcode))
            System.out.println("Item has already exceeded the maximum amount of times it can be loaned");

        Loan renewLoan = getLoan(barcode);
        renewLoan.increaseNumRenews();
        renewLoan.setDueDate(getDueDate(barcode));


    }

    public void returnItem(int barcode){
        Loan returnLoan = getLoan(barcode);
        loans.remove(returnLoan);

    }

    private LocalDate getDueDate(int barcode){
        Item item = getItem(barcode);
        if(item.getType().equals(Item.ItemType.Book))
            return LocalDate.now().plusWeeks(4);
        else if(item.getType().equals(Item.ItemType.Multimedia))
            return LocalDate.now().plusWeeks(1);
        else return LocalDate.now();

    }

    private Loan getLoan(int barcode){
        for(Loan loan: loans){
            if(loan.getItemBarcode() == barcode)
                return loan;

        }
        return new Loan();
    }

    private Item getItem(int barcode){
        for(Item item: items){
            if(item.getBarcode() == barcode)
                return item;

        }
        return new Item();
    }

    private User getUser(String userID){
        for(User user: users){
            if(user.getUserID().equals(userID))
                return user;

        }
        return new User();
    }


    private boolean barcodeDoesNotExist(int barcode){
       Item item = getItem(barcode);
       return item.getBarcode() == 0;
    }



    private boolean userIDDoesNotExist(String userID){
        User user = getUser(userID);
        return user.getUserID().equals("");


    }

    private boolean itemAlreadyLoaned(int barcode){
        Loan loan = getLoan(barcode);
        return loan.getDueDate().isAfter(LocalDate.now());

    }

    private boolean exceedMaxRenews(int barcode){
        Loan loan = getLoan(barcode);
        Item item = getItem(barcode);
        if(item.getType().equals(Item.ItemType.Book)) {
            return loan.getNumRenews() >= 3;
        }
        else
            return loan.getNumRenews() >= 2;
    }






}

