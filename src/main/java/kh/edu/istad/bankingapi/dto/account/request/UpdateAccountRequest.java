package kh.edu.istad.bankingapi.dto.account.request;

import java.math.BigDecimal;

public record UpdateAccountRequest(
        BigDecimal overLimit
) { }
