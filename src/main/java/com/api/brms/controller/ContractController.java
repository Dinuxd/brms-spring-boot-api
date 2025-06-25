package com.api.brms.controller;

import com.api.brms.entity.Contract;
import com.api.brms.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService cs) { this.contractService = cs; }

    @GetMapping("/by-booking/{bookingId}")
    public ResponseEntity<Contract> byBooking(@PathVariable Long bookingId) {
        Contract c = contractService.getContractByBookingId(bookingId);
        return (c != null) ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/sign")
    public ResponseEntity<Contract> sign(@PathVariable Long id) {
        Contract c = contractService.signContract(id);
        return ResponseEntity.ok(c);
    }
}
