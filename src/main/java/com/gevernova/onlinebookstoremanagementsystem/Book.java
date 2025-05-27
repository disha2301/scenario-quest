package com.gevernova.onlinebookstoremanagementsystem;

// Book.java - encapsulation : Represents a book entity only
public class Book {

    // data members
    private String title;
    private Author author;
    private double price;
    private String category;

    // constructors
    public Book(String title, Author author, double price, String category) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
    }

    // getters
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}
