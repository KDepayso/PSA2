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
                    throw new BarCodeNotFoundException();
                if(userIDDoesNotExist(userID))
                    throw new UserIDNotFoundException();
                if(itemAlreadyLoaned(barcode)){
                    System.out.println("Item already loaned");
                    break;
                }
                else{
                    System.out.println("Item has been successfully loaned");
                    LocalDate issueDate = getDueDate(barcode);
                    Loan newLoan = new Loan(barcode,userID.toUpperCase(Locale.ROOT),issueDate);
                    loans.add((newLoan));
                    break;
                }

            }
            catch (BarCodeNotFoundException ex){
                System.out.println("BarCode does not exist, please try again");
                barcode = Integer.parseInt(scanner.nextLine());
            }
            catch (UserIDNotFoundException ex){
                System.out.println("UserID doesn't exist, please try again");
                userID = scanner.nextLine().toUpperCase();
            }

        }

    }

    public void renewLoan(int barcode){
        if(barcodeDoesNotExistLoan(barcode))
            System.out.println("BarCode doesn't exist or is not currently loaned out");
        else if(exceedMaxRenews(barcode))
            System.out.println("Item has already exceeded the maximum amount of times it can be loaned");
        else{
            Loan renewLoan = getLoan(barcode);
            renewLoan.increaseNumRenews();
            renewLoan.setDueDate(getDueDate(barcode));

            System.out.println("Item has been renewed, new due date is " + renewLoan.getDueDateAsString());
        }



    }

    public void returnItem(int barcode){
        if(barcodeDoesNotExistLoan(barcode))
            System.out.println("Item is not currently loaned out");
        else{
            if(lateReturn(barcode))
                System.out.println("Item is returned late");
            Loan returnLoan = getLoan(barcode);
            loans.remove(returnLoan);
            System.out.printf("Barcode %s has been removed from loan list%n",barcode);

        }

    }



    private LocalDate getDueDate(int barcode){
        Item item = getItem(barcode);
        if(item.getType().equals(Item.ItemType.Book))
            return LocalDate.now().plusWeeks(4);
        else if(item.getType().equals(Item.ItemType.Multimedia))
            return LocalDate.now().plusWeeks(1);
        else return LocalDate.now();

    }

    private boolean lateReturn(int barcode){
        Loan returnLoan = getLoan(barcode);
        return returnLoan.getDueDate().isBefore(LocalDate.now());
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

    private boolean barcodeDoesNotExistLoan(int barcode){
        Loan loan = getLoan(barcode);
        return loan.getItemBarcode() == 0;
    }



    private boolean userIDDoesNotExist(String userID){
        User user = getUser(userID);
        return user.getUserID().equals("");


    }

    private boolean itemAlreadyLoaned(int barcode){
        Loan loan = getLoan(barcode);
        return loan.getItemBarcode() != 0;

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

