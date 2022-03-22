package com.company;

public class User implements Printable{

    private String userID = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";


    public User() {
    }

    public User(String userID, String firstName, String lastName, String email) {
        setUserID(userID);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return String.format("User-ID = %s  First Name = %s  Last Name = %s  Email =  %s ",
                userID,firstName,lastName,email);
    }
}
