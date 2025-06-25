package com.api.brms.service.impl;

import com.api.brms.entity.Listing;
import com.api.brms.repository.ListingRepository;
import com.api.brms.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepo;

    @Autowired
    public ListingServiceImpl(ListingRepository listingRepo) {
        this.listingRepo = listingRepo;
    }

    @Override
    public Listing createListing(Listing listing) {
        listing.setId(null);
        listing.setAvailable(true);
        return listingRepo.save(listing);
    }

    @Override
    public Listing getListingById(Long id) {
        return listingRepo.findById(id).orElse(null);
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepo.findAll();
    }

    @Override
    public Listing updateListing(Long id, Listing listing) {
        Listing ex = listingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        ex.setTitle(listing.getTitle());
        ex.setDescription(listing.getDescription());
        ex.setLocation(listing.getLocation());
        ex.setPrice(listing.getPrice());
        ex.setAvailable(listing.getAvailable());
        return listingRepo.save(ex);
    }

    @Override
    public void deleteListing(Long id) {
        listingRepo.deleteById(id);
    }

    @Override
    public List<Listing> searchListings(String kw) {
        if (kw == null || kw.isEmpty()) {
            return listingRepo.findAll();
        }
        return listingRepo.findByLocationContainingIgnoreCase(kw);
    }
}
