package kh.edu.istad.bankingapi.dto.account.response;

import java.math.BigDecimal;

public record AccountResponse(
        String accountNumber,
        String accountName,
        BigDecimal balance,
        BigDecimal overLimit,
        String accountType
) {
}
