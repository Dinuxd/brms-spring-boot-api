package com.api.brms.repository;

import com.api.brms.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByLocationContainingIgnoreCase(String location);
}
