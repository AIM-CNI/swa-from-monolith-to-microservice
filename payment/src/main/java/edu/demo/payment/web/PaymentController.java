package edu.demo.payment.web;

import edu.demo.payment.PaymentRequest;
import edu.demo.payment.service.PaymentService;
import edu.demo.payment.web.dto.PaymentReceipt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) { this.paymentService = paymentService; }

    @PostMapping("/create")
    public ResponseEntity<PaymentReceipt> createPayment(@RequestBody PaymentRequest paymentRequest) {
        boolean succeeded = paymentService.pay(paymentRequest.total(), paymentRequest.token());
        return ResponseEntity.ok(new PaymentReceipt(succeeded));
    }
}
