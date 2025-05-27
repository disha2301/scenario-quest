package com.gevernova.onlinebookstoremanagementsystem;

// Cart.java - SRP: Manages cart operations only

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> items = new ArrayList<>();

    // method to add a book with the use of custom exception
    public void addBook(Book book) throws InvalidBookException{
        if(book == null ) throw new InvalidBookException("Can't add a null value");
        items.add(book);
    }

    // method to remove a book
    public void removeBook(Book book) throws InvalidBookException{
        if(!items.contains(book)) throw new InvalidBookException("Book not found in cart.");
        items.remove(book);
    }

    // getter
    public List<Book> getItems(){
        return new ArrayList<>(items);
    }
}
