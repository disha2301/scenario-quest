package com.gevernova;// CartServiceTest.java - Unit tests for Cart and Discount features
import com.gevernova.onlinebookstoremanagementsystem.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CartServiceTest {

    @Test
    public void testCartWithFlatDiscount() throws Exception {
        Author author = new Author("J.K. Rowling");
        Book b1 = new Book("Book1", author, 500, "Fiction");
        Book b2 = new Book("Book2", author, 400, "Fiction");

        Cart cart = new Cart();
        cart.addBook(b1);
        cart.addBook(b2);

        CartPriceCalculator priceCalc = new CartPriceCalculator();
        double total = priceCalc.calculateTotal(cart);

        DiscountCalculator discount = new FlatDiscountCalculator(300);
        DiscountContext context = new DiscountContext(discount);
        double discounted = context.applyDiscount(total);

        assertEquals(600.0, discounted, 0.01);
    }

    @Test(expected = InvalidBookException.class)
    public void testRemoveInvalidBook() throws Exception {
        Cart cart = new Cart();
        Author author = new Author("Unknown");
        Book book = new Book("Ghost Book", author, 100, "Mystery");
        cart.removeBook(book); // should throw
    }
}
