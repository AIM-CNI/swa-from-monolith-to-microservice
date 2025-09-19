package edu.demo.payment.service;

import edu.demo.auth.AuthApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {
    private final AuthApi authApi;
    private Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final Random random = new Random();

    public PaymentService(AuthApi authApi) {
        this.authApi = authApi;
    }

    // Simuleer een betaling; 85% kans op succes
    public boolean pay(double amount, String token) {
        boolean succeeded = random.nextDouble() < 0.85;
        String result = succeeded ? "succeeded" : "failed";
        logger.info("Payment service " + result + " for user: " + authApi.validateUser(token) + ", amount: " + amount);
        return succeeded;
    }
}
