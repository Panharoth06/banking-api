package kh.edu.istad.bankingapi.repository;

import kh.edu.istad.bankingapi.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KYC, String> {
}
