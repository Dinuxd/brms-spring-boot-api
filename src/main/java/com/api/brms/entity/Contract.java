package com.api.brms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private String terms;
    private Boolean signed;

    public Contract() {}
    public Contract(Long id, Booking booking, String terms, Boolean signed) {
        this.id = id; this.booking = booking;
        this.terms = terms; this.signed = signed;
    }
    // getters & setters...
    public Long getId() { return id; }
    public void setId(Long i) { this.id = i; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking b) { this.booking = b; }
    public String getTerms() { return terms; }
    public void setTerms(String t) { this.terms = t; }
    public Boolean getSigned() { return signed; }
    public void setSigned(Boolean s) { this.signed = s; }
}
