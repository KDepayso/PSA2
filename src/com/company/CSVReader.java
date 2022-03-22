package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CSVReader {

    ArrayList<Item> csvItem = new ArrayList<>();
    ArrayList<User> csvUser = new ArrayList<>();
    ArrayList<Loan> csvLoan = new ArrayList<>();

    public ArrayList<Item> readItemsCSV(){
        String file = "src\\ITEMS.csv";
        BufferedReader reader;
        String currentLine;
        int barcode;
        String author;
        String title;
        String type;
        int year;
        String isbn;


        try{
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((currentLine = reader.readLine()) != null){

                String[] itemArray = currentLine.split(",");

                barcode = Integer.parseInt(itemArray[0]);
                author= itemArray[1];
                title = itemArray[2];
                type = itemArray[3];
                year = Integer.parseInt(itemArray[4]);
                isbn = itemArray[5];

                Item item = new Item(barcode,author,title,type,year,isbn);

                csvItem.add(item);


            }
        }
        catch (Exception e){

        }
        finally {
            return csvItem;
        }

    }

    public ArrayList<User> readUsersCSV(){
        String file = "src\\USERS.csv";
        BufferedReader reader;
        String currentLine;
        String userID;
        String firstName;
        String lastName;
        String email;


        try{
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((currentLine = reader.readLine()) != null){

                String[] userArray = currentLine.split(",");

                userID= userArray[0];
                firstName = userArray[1];
                lastName = userArray[2];
                email = userArray[3];

                User user = new User(userID,firstName,lastName,email);

                csvUser.add(user);
            }
        }
        catch (Exception e){

        }
        finally {
            return csvUser;
        }
    }


    public ArrayList<Loan> readLoansCSV(){
        String file = "src\\LOANS.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        BufferedReader reader;
        String currentLine;
        int barcode;
        String userID;
        LocalDate issueDate;
        LocalDate dueDate;
        int numRenews;


        try{
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((currentLine = reader.readLine()) != null){

                String[] loanArray = currentLine.split(",");

                barcode = Integer.parseInt(loanArray[0]);
                userID= loanArray[1];
                issueDate = LocalDate.parse(loanArray[2],formatter);
                dueDate = LocalDate.parse(loanArray[3],formatter);
                numRenews = Integer.parseInt(loanArray[4]);


                Loan loan = new Loan(barcode,userID,issueDate,dueDate,numRenews);

                csvLoan.add(loan);
            }
        }
        catch (Exception e){

        }
        finally {
            return csvLoan;
        }
    }

}
