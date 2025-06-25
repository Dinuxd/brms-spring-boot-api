package com.api.brms.repository;

import com.api.brms.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {
    List<MaintenanceRequest> findByListingId(Long listingId);
    List<MaintenanceRequest> findByRequesterId(Long userId);
}
