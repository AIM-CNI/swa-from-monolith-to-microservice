package edu.demo.order.service;

public class InvalidOrderException extends IllegalArgumentException {
    public InvalidOrderException(String mismatchedItems) {
        super(mismatchedItems);
    }
}
