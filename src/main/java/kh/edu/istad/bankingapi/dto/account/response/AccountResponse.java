package kh.edu.istad.bankingapi.dto.account.response;

import java.math.BigDecimal;

public record AccountResponse(
        String actName,
        String actNo,
        BigDecimal balance,
        BigDecimal overLimit,
        String accountType
) {
}
