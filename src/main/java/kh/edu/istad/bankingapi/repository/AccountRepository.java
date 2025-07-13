package kh.edu.istad.bankingapi.repository;

import jakarta.validation.constraints.NotBlank;
import kh.edu.istad.bankingapi.domain.Account;
import kh.edu.istad.bankingapi.dto.account.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByActNo(String actNo);

    Optional<AccountResponse> findAccountByActNo(String actNo);

//    boolean existsByAccountNumber(@NotBlank String accountNumber);

}
