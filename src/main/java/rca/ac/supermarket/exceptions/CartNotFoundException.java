package rca.ac.supermarket.exceptions;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super(message);
    }
}
