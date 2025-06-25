package com.api.brms.controller;

import com.api.brms.entity.Payment;
import com.api.brms.dto.PaymentRequest;
import com.api.brms.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService ps) { this.paymentService = ps; }

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> pay(
            @PathVariable Long bookingId,
            @RequestBody PaymentRequest req) {
        Payment p = paymentService.processPayment(bookingId, req);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> getByBooking(@PathVariable Long bookingId) {
        Payment p = paymentService.getPaymentByBooking(bookingId);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }
}
