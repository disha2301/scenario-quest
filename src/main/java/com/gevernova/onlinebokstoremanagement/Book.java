package com.gevernova.onlinebokstoremanagement;

public class Book {
    private String title;
    private Author author;
    private double price;
    private String category;

    public Book(String title, Author author, double price, String category) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
    }

    // Getters
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}
