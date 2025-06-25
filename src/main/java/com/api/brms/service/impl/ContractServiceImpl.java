package com.api.brms.service.impl;

import com.api.brms.entity.Contract;
import com.api.brms.repository.ContractRepository;
import com.api.brms.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepo;

    @Autowired
    public ContractServiceImpl(ContractRepository cr) {
        this.contractRepo = cr;
    }

    @Override
    public Contract getContractByBookingId(Long bookingId) {
        return contractRepo.findByBookingId(bookingId);
    }

    @Override
    public Contract signContract(Long contractId) {
        Contract c = contractRepo.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        c.setSigned(true);
        return contractRepo.save(c);
    }
}
