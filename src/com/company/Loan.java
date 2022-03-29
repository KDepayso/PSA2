package com.company;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Loan implements Printable{

    private int itemBarcode = 0;
    private String userID = "";
    private LocalDate issueDate = LocalDate.now();
    private LocalDate dueDate = LocalDate.now();
    private int numRenews = 0;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Loan() {
    }

    public Loan(int itemBarcode, String userID, LocalDate dueDate) {
        setItemBarcode(itemBarcode);
        setUserID(userID.toUpperCase(Locale.ROOT));
        setIssueDate();
        setDueDate(dueDate);
        numRenews = 0;
    }

    public Loan(int itemBarcode, String userID, LocalDate issueDate, LocalDate dueDate, int numRenews){
        setItemBarcode(itemBarcode);
        setUserID(userID.toUpperCase(Locale.ROOT));
        setIssueDate(issueDate);
        setDueDate(dueDate);
        setNumRenews(numRenews);
    }

    public int getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(int itemBarcode) {this.itemBarcode = itemBarcode;}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public LocalDate getIssueDate() {return issueDate;}

    public String getIssueDateAsString(){
        return issueDate.format(formatter);
    }

    private void setIssueDate() {
        this.issueDate = LocalDate.now();
    }
    private void setIssueDate(LocalDate issueDate) {this.issueDate = issueDate;}

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDueDateAsString(){
        return dueDate.format(formatter);
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getNumRenews() {
        return numRenews;
    }

    public void setNumRenews(int numRenews) {this.numRenews = numRenews; }

    public void increaseNumRenews() {this.numRenews += 1;}





    @Override
    public String toString(){
        return String.format("Barcode = %s  User-ID = %s  Issue Date = %s  Due Date =  %s  Num Renews = %s",
                itemBarcode,userID,issueDate,dueDate,numRenews);
    }
}
