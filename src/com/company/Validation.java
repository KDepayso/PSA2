package com.company;
import java.util.ArrayList;

public class Validation {

    public boolean DoesBarCodeExist(int barcode,ArrayList<Item> items){
        for(Item index: items){
            if(index.getBarcode() == barcode){
                return true;
            }
        }
        return false;
    }

    public boolean DoesUserIDExist(String userID, ArrayList<User> users ){

        for(User index: users){
            if(index.getUserID() == userID){
                return true;
            }
        }
        return false;
    }



}
