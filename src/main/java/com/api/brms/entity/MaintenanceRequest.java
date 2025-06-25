package com.api.brms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User requester;

    private String description;
    private String status;
    private LocalDateTime createdAt;

    public MaintenanceRequest() {}
    public MaintenanceRequest(Long id, Listing listing, User requester,
                              String description, String status, LocalDateTime createdAt) {
        this.id = id; this.listing = listing; this.requester = requester;
        this.description = description; this.status = status; this.createdAt = createdAt;
    }
    // getters & setters...
    public Long getId() { return id; }
    public void setId(Long i) { this.id = i; }
    public Listing getListing() { return listing; }
    public void setListing(Listing l) { this.listing = l; }
    public User getRequester() { return requester; }
    public void setRequester(User u) { this.requester = u; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime t) { this.createdAt = t; }
}
