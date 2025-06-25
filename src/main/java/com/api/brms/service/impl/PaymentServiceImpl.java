package com.api.brms.service.impl;

import com.api.brms.entity.Booking;
import com.api.brms.entity.Payment;
import com.api.brms.repository.BookingRepository;
import com.api.brms.repository.PaymentRepository;
import com.api.brms.service.PaymentService;
import com.api.brms.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final BookingRepository bookingRepo;
    private final PaymentRepository paymentRepo;

    @Autowired
    public PaymentServiceImpl(BookingRepository br, PaymentRepository pr) {
        this.bookingRepo = br;
        this.paymentRepo = pr;
    }

    @Override
    public Payment processPayment(Long bookingId, PaymentRequest req) {
        Booking b = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        Payment p = new Payment();
        p.setBooking(b);
        p.setAmount(req.getAmount());
        p.setPaidAt(LocalDateTime.now());
        p.setStatus("SUCCESS");
        return paymentRepo.save(p);
    }

    @Override
    public Payment getPaymentByBooking(Long bookingId) {
        return paymentRepo.findByBookingId(bookingId);
    }
}
