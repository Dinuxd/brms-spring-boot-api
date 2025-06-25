package com.api.brms.service;

import com.api.brms.entity.Payment;
import com.api.brms.dto.PaymentRequest;

public interface PaymentService {
    Payment processPayment(Long bookingId, PaymentRequest paymentRequest);
    Payment getPaymentByBooking(Long bookingId);
}
