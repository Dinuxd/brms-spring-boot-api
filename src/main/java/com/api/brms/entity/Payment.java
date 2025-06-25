package com.api.brms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private Double amount;
    private LocalDateTime paidAt;
    private String status;

    public Payment() {}
    public Payment(Long id, Booking booking,
                   Double amount, LocalDateTime paidAt, String status) {
        this.id = id; this.booking = booking;
        this.amount = amount; this.paidAt = paidAt; this.status = status;
    }
    // getters & setters...
    public Long getId() { return id; }
    public void setId(Long i) { this.id = i; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking b) { this.booking = b; }
    public Double getAmount() { return amount; }
    public void setAmount(Double a) { this.amount = a; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime t) { this.paidAt = t; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
}
