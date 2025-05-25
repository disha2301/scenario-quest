package com.gevernova;

import com.gevernova.onlinebokstoremanagement.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @Test
    public void testAddAndRemoveBook() {
        Cart cart = new Cart();
        Book book = new Book("Java Basics", new Author("John Doe"), 500, "Programming");

        cart.addBook(book);
        assertEquals(1, cart.getBooks().size(), "Book should be added to the cart");

        cart.removeBook(book);
        assertEquals(0, cart.getBooks().size(), "Book should be removed from the cart");
    }

    @Test
    public void testCartTotalWithPercentageDiscount() {
        Cart cart = new Cart();
        cart.addBook(new Book("Java", new Author("A"), 100, "Programming"));
        cart.addBook(new Book("Python", new Author("B"), 200, "Programming"));

        DiscountStrategy strategy = new PercentageDiscount(10); // 10% off
        double total = cart.calculateTotal(strategy);

        assertEquals(270.0, total, 0.01, "Total should reflect 10% discount");
    }

    @Test
    public void testCartTotalWithoutDiscount() {
        Cart cart = new Cart();
        cart.addBook(new Book("C#", new Author("E"), 300, "Programming"));

        DiscountStrategy strategy = new NoDiscount();
        double total = cart.calculateTotal(strategy);

        assertEquals(300.0, total, 0.01, "Total should reflect no discount");
    }

    @Test
    public void testCalculateTotalWithNullDiscountThrowsException() {
        Cart cart = new Cart();
        cart.addBook(new Book("Kotlin", new Author("F"), 400, "Programming"));

        assertThrows(InvalidDiscountException.class, () -> {
            cart.calculateTotal(null);
        }, "Should throw InvalidDiscountException when discount strategy is null");
    }

    @Test
    public void testRemoveNonExistentBook() {
        Cart cart = new Cart();
        Book book1 = new Book("Swift", new Author("G"), 200, "Programming");
        Book book2 = new Book("Scala", new Author("H"), 300, "Programming");

        cart.addBook(book1);
        cart.removeBook(book2); // book2 was never added

        assertEquals(1, cart.getBooks().size(), "Removing a non-existent book should not change the cart");
    }
}
