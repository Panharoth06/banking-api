package kh.edu.istad.bankingapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kh.edu.istad.bankingapi.domain.KYC;
import kh.edu.istad.bankingapi.repository.KYCRepository;
import kh.edu.istad.bankingapi.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KYCServiceImpl implements KYCService {
    private final KYCRepository  kycRepository;


    @Override
    public void verifyCustomerByNationalCardId(String nationalCardId) {
        KYC kyc = kycRepository
                .findByNationalCardId(nationalCardId)
                .orElseThrow(()->new EntityNotFoundException("NationalCardId not found"));

        kyc.setIsVerified(true);
        kycRepository.save(kyc);
    }
}
