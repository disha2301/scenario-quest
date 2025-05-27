package com.gevernova.onlinebookstoremanagementsystem;

import java.util.List;
import java.util.stream.Collectors;

// BookSearchService.java - SRP: Handles search functionality only
public class BookSearchService {
    private List<Book> books;

    // constructor
    public BookSearchService(List<Book> books){
        this.books = books;
    }
    // method to search the book by title
    public List<Book> searchByTitle(String title){
        return books.stream()
                .filter(b->b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
    //method to search the book by author
    public List<Book> searchByAuthor(String authorName){
        return books.stream()
                .filter(b->b.getAuthor().getName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }
}
