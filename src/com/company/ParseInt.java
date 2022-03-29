package com.company;

import java.util.Scanner;

public class ParseInt {

    Scanner scanner = new Scanner(System.in);

    public int parseInt(){
        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e){
                System.out.println("Invalid input detected, please try again.");
            }
        }
    }
}
