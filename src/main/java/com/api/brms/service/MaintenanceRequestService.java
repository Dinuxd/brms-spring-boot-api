package com.api.brms.service;

import com.api.brms.entity.MaintenanceRequest;
import java.util.List;

public interface MaintenanceRequestService {
    MaintenanceRequest createRequest(Long listingId, Long userId, String description);
    List<MaintenanceRequest> getRequestsByListing(Long listingId);
    List<MaintenanceRequest> getRequestsByUser(Long userId);
    MaintenanceRequest updateStatus(Long requestId, String status);
    MaintenanceRequest getRequestById(Long id);
}
