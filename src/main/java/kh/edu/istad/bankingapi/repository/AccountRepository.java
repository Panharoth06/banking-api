package kh.edu.istad.bankingapi.repository;

import kh.edu.istad.bankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByActNo(String actNo);

    Optional<Account> findAccountByActNo(String actNo);

    List<Account> findAccountsByCustomerPhoneNumber(String customerPhoneNumber);

    List<Account> findAccountsByIsDeletedFalse();
}
