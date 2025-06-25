package com.api.brms.repository;

import com.api.brms.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract findByBookingId(Long bookingId);
}
