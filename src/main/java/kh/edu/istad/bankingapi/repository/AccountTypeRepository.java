package kh.edu.istad.bankingapi.repository;

import kh.edu.istad.bankingapi.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    Optional<AccountType> findAccountTypesByTypeIgnoreCase(String type);
}
