package com.company;

public class Item implements Printable{


    private int barcode = 0;
    private String author = "";
    private String title = "";
    private ItemType type = ItemType.NotFound;
    private int year = 0;
    private String isbn = "";

    public enum ItemType{
        Book,Multimedia,NotFound
    }


    public Item() {
    }

    public Item(int barcode, String author, String title, String type, int year, String isbn) {
        setBarcode(barcode);
        setAuthor(author);
        setTitle(title);
        setType(type);
        setYear(year);
        setIsbn(isbn);
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(String type) {
        if(type.equals("Book"))
            this.type = ItemType.Book;
        else if(type.equals("Multimedia"))
            this.type = ItemType.Multimedia;
        else this.type = ItemType.NotFound;

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString(){
        return String.format("Barcode = %s  Author = %s  Title = %s  Type =  %s Year = %s  ISBN = %s",
                barcode,author,title,type,year,isbn);
    }
}
