package edu.demo.payment;

public interface PaymentApi {
    boolean pay(double total, String token);
}
