package com.api.brms.service;

import com.api.brms.entity.Listing;
import java.util.List;

public interface ListingService {
    Listing createListing(Listing listing);
    Listing getListingById(Long id);
    List<Listing> getAllListings();
    Listing updateListing(Long id, Listing listing);
    void deleteListing(Long id);
    List<Listing> searchListings(String locationKeyword);
}
