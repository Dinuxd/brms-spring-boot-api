package com.api.brms.controller;

import com.api.brms.entity.Booking;
import com.api.brms.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bs) { this.bookingService = bs; }

    @PostMapping
    public ResponseEntity<Booking> create(
            @RequestParam Long listingId,
            @RequestParam Long userId,
            @RequestBody Booking booking) {
        Booking c = bookingService.createBooking(listingId, userId, booking);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> byId(@PathVariable Long id) {
        Booking b = bookingService.getBookingById(id);
        return (b != null) ? ResponseEntity.ok(b) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public List<Booking> byUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @GetMapping("/listing/{listingId}")
    public List<Booking> byListing(@PathVariable Long listingId) {
        return bookingService.getBookingsByListing(listingId);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancel(@PathVariable Long id) {
        Booking b = bookingService.cancelBooking(id);
        return ResponseEntity.ok(b);
    }
}
