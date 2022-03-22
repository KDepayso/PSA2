package com.company;

import java.util.ArrayList;

public class ViewList {

    //Item, User and Loan implement the printable interface, allowing them to be extended.
    public void ViewList(ArrayList<? extends Printable> printable){
        for(Printable index: printable){
            System.out.println(index.toString());
        }
    }

}
