package com.api.brms.controller;

import com.api.brms.entity.MaintenanceRequest;
import com.api.brms.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance-requests")
public class MaintenanceRequestController {
    private final MaintenanceRequestService reqService;

    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService mrs) {
        this.reqService = mrs;
    }

    @PostMapping
    public ResponseEntity<MaintenanceRequest> create(
            @RequestParam Long listingId,
            @RequestParam Long userId,
            @RequestBody String description) {
        MaintenanceRequest r = reqService.createRequest(listingId, userId, description);
        return ResponseEntity.status(201).body(r);
    }

    @GetMapping("/listing/{listingId}")
    public List<MaintenanceRequest> byListing(@PathVariable Long listingId) {
        return reqService.getRequestsByListing(listingId);
    }

    @GetMapping("/user/{userId}")
    public List<MaintenanceRequest> byUser(@PathVariable Long userId) {
        return reqService.getRequestsByUser(userId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        MaintenanceRequest u = reqService.updateStatus(id, status);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> byId(@PathVariable Long id) {
        MaintenanceRequest r = reqService.getRequestById(id);
        return (r != null) ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }
}
