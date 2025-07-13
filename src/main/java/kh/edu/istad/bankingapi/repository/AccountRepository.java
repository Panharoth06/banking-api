package kh.edu.istad.bankingapi.repository;

import kh.edu.istad.bankingapi.domain.Account;
import kh.edu.istad.bankingapi.dto.account.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByActNo(String actNo);

    Optional<AccountResponse> findAccountByActNo(String actNo);

    List<AccountResponse> findAccountsByCustomerPhoneNumber(String customerPhoneNumber);

    List<AccountResponse> findAccountsByIsDeletedFalse();
}
