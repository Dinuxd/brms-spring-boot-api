package com.api.brms.repository;

import com.api.brms.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByBookingId(Long bookingId);
}
