package com.api.brms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "listings")
public class Listing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private Double price;
    private Boolean available;

    public Listing() {}
    public Listing(Long id, String title, String description,
                   String location, Double price, Boolean available) {
        this.id = id; this.title = title; this.description = description;
        this.location = location; this.price = price; this.available = available;
    }

    // getters & setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public String getLocation() { return location; }
    public void setLocation(String l) { this.location = l; }
    public Double getPrice() { return price; }
    public void setPrice(Double p) { this.price = p; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean a) { this.available = a; }
}
