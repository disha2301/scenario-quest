package com.gevernova.onlinebokstoremanagement;

import java.util.*;
import java.util.stream.Collectors;

public class BookstoreService {
    private List<Book> books = new ArrayList<>();
    private Map<String, List<Book>> categoryMap = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
        categoryMap.computeIfAbsent(book.getCategory(), k -> new ArrayList<>()).add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
        if (categoryMap.containsKey(book.getCategory())) {
            categoryMap.get(book.getCategory()).remove(book);
        }
    }

    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String authorName) {
        return books.stream()
                .filter(b -> b.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByCategory(String category) {
        return categoryMap.getOrDefault(category, Collections.emptyList());
    }
}
