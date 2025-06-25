package com.api.brms.service.impl;

import com.api.brms.entity.Booking;
import com.api.brms.entity.Contract;
import com.api.brms.entity.Listing;
import com.api.brms.entity.User;
import com.api.brms.repository.BookingRepository;
import com.api.brms.repository.ContractRepository;
import com.api.brms.repository.ListingRepository;
import com.api.brms.repository.UserRepository;
import com.api.brms.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepo;
    private final ListingRepository listingRepo;
    private final UserRepository userRepo;
    private final ContractRepository contractRepo;

    @Autowired
    public BookingServiceImpl(BookingRepository br, ListingRepository lr,
                              UserRepository ur, ContractRepository cr) {
        this.bookingRepo = br;
        this.listingRepo = lr;
        this.userRepo = ur;
        this.contractRepo = cr;
    }

    @Override
    public Booking createBooking(Long listingId, Long userId, Booking details) {
        Listing listing = listingRepo.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        details.setId(null);
        details.setListing(listing);
        details.setUser(user);
        details.setActive(true);
        Booking saved = bookingRepo.save(details);

        // create contract
        Contract c = new Contract();
        c.setBooking(saved);
        c.setTerms("Standard terms...");
        c.setSigned(false);
        contractRepo.save(c);

        listing.setAvailable(false);
        listingRepo.save(listing);

        return saved;
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsByListing(Long listingId) {
        return bookingRepo.findByListingId(listingId);
    }

    @Override
    public Booking cancelBooking(Long id) {
        Booking b = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        b.setActive(false);
        bookingRepo.save(b);
        Listing listing = b.getListing();
        listing.setAvailable(true);
        listingRepo.save(listing);
        return b;
    }
}
