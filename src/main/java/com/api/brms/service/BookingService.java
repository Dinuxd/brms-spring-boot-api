package com.api.brms.service;

import com.api.brms.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Long listingId, Long userId, Booking bookingDetails);
    Booking getBookingById(Long id);
    List<Booking> getBookingsByUser(Long userId);
    List<Booking> getBookingsByListing(Long listingId);
    Booking cancelBooking(Long id);
}
