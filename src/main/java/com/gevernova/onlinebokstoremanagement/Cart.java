package com.gevernova.onlinebokstoremanagement;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void removeBook(Book book) { books.remove(book); }
    public List<Book> getBooks() { return books; }

    public double calculateTotal(DiscountStrategy strategy) {
        if (strategy == null) {
            throw new InvalidDiscountException("Discount strategy cannot be null.");
        }

        if (books.isEmpty()) {
            throw new EmptyCartException("Cannot calculate total for an empty cart.");
        }

        double total = books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
        return strategy.applyDiscount(total);
    }
}
