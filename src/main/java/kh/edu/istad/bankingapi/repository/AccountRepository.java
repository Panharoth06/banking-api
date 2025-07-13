package kh.edu.istad.bankingapi.repository;

import jakarta.validation.constraints.NotBlank;
import kh.edu.istad.bankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByActNo(String actNo);

//    boolean existsByAccountNumber(@NotBlank String accountNumber);

}
