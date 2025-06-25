package com.api.brms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;

    public Booking() {}
    public Booking(Long id, Listing listing, User user,
                   LocalDate startDate, LocalDate endDate, Boolean active) {
        this.id = id; this.listing = listing; this.user = user;
        this.startDate = startDate; this.endDate = endDate; this.active = active;
    }
    // getters & setters...
    public Long getId() { return id; }
    public void setId(Long i) { this.id = i; }
    public Listing getListing() { return listing; }
    public void setListing(Listing l) { this.listing = l; }
    public User getUser() { return user; }
    public void setUser(User u) { this.user = u; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate d) { this.startDate = d; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate d) { this.endDate = d; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean a) { this.active = a; }
}
