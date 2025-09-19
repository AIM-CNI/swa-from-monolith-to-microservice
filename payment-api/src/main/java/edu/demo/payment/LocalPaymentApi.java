package edu.demo.payment;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LocalPaymentApi implements PaymentApi {
    private final RestClient restClient;

    public LocalPaymentApi() {
        this.restClient = RestClient.create();
    }

    @Override
    public boolean pay(double total, String token) {
        return restClient.post()
                .uri("http://localhost:8083/api/payment/create")
                .body(new PaymentRequest(total, token))
                .retrieve()
                .body(PaymentReceipt.class)
                .succeeded();
    }
}
