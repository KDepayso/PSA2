package com.company;


import java.io.File;

import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVWriter {


    public PrintWriter createPrintWriter(){
        File file = new File("src\\LOANS.csv");
        try{
            return new PrintWriter(file);
        }
        catch (Exception e){
            System.out.println("There was am error in writing to the file");
        }
        return null;
    }



    public void writeLoanCSV(ArrayList<Loan> loanlist){
        PrintWriter out = createPrintWriter();
        out.println("Barcode,User_id,Issue_Date,Due_Date,numRenews");
        for(Loan loan: loanlist){
            out.printf("%d,%s,%s,%s,%d\n",loan.getItemBarcode(),loan.getUserID(),loan.getIssueDateAsString(),
                    loan.getDueDateAsString(),loan.getNumRenews());
        }
        out.close();

       }



}
