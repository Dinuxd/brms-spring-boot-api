package com.api.brms.service.impl;

import com.api.brms.entity.Listing;
import com.api.brms.entity.MaintenanceRequest;
import com.api.brms.entity.User;
import com.api.brms.repository.ListingRepository;
import com.api.brms.repository.MaintenanceRequestRepository;
import com.api.brms.repository.UserRepository;
import com.api.brms.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {
    private final MaintenanceRequestRepository reqRepo;
    private final ListingRepository listingRepo;
    private final UserRepository userRepo;

    @Autowired
    public MaintenanceRequestServiceImpl(MaintenanceRequestRepository rr,
                                         ListingRepository lr,
                                         UserRepository ur) {
        this.reqRepo = rr;
        this.listingRepo = lr;
        this.userRepo = ur;
    }

    @Override
    public MaintenanceRequest createRequest(Long listingId, Long userId, String desc) {
        Listing l = listingRepo.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        MaintenanceRequest r = new MaintenanceRequest();
        r.setListing(l);
        r.setRequester(u);
        r.setDescription(desc);
        r.setStatus("OPEN");
        r.setCreatedAt(LocalDateTime.now());
        return reqRepo.save(r);
    }

    @Override
    public List<MaintenanceRequest> getRequestsByListing(Long listingId) {
        return reqRepo.findByListingId(listingId);
    }

    @Override
    public List<MaintenanceRequest> getRequestsByUser(Long userId) {
        return reqRepo.findByRequesterId(userId);
    }

    @Override
    public MaintenanceRequest updateStatus(Long requestId, String status) {
        MaintenanceRequest r = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        r.setStatus(status);
        return reqRepo.save(r);
    }

    @Override
    public MaintenanceRequest getRequestById(Long id) {
        return reqRepo.findById(id).orElse(null);
    }
}
