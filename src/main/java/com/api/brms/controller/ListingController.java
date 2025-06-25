package com.api.brms.controller;

import com.api.brms.entity.Listing;
import com.api.brms.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingController {
    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService ls) { this.listingService = ls; }

    @GetMapping
    public List<Listing> all(@RequestParam(required = false) String location) {
        if (location != null) return listingService.searchListings(location);
        return listingService.getAllListings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> byId(@PathVariable Long id) {
        Listing l = listingService.getListingById(id);
        return (l != null) ? ResponseEntity.ok(l) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Listing> create(@RequestBody Listing listing) {
        Listing c = listingService.createListing(listing);
        return ResponseEntity.status(201).body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> update(
            @PathVariable Long id, @RequestBody Listing listing) {
        Listing u = listingService.updateListing(id, listing);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }
}
