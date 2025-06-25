package com.api.brms.service;

import com.api.brms.entity.Contract;

public interface ContractService {
    Contract getContractByBookingId(Long bookingId);
    Contract signContract(Long contractId);
}
