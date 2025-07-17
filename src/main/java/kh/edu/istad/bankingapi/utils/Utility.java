package kh.edu.istad.bankingapi.utils;

import kh.edu.istad.bankingapi.domain.Account;
import kh.edu.istad.bankingapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Utility {
    private final AccountRepository accountRepository;

    public void generateAccountNumber(Account account) {
        String prefix = "ACT";
        String newActNo;
        do {
            newActNo = prefix + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (accountRepository.existsByAccountNumber((newActNo)));
        account.setAccountNumber(newActNo);
    }


}
