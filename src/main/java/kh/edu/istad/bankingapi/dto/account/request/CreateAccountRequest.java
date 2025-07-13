package kh.edu.istad.bankingapi.dto.account.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateAccountRequest(

        @NotBlank(message = "Account name is required")
        String accountName,

        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @DecimalMin(value = "5.0", message = "Over limit cannot be lower than 5")
        BigDecimal overLimit,
//
        @NotBlank(message = "Account type is required")
        String accountType
) {
}
