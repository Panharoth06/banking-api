package kh.edu.istad.bankingapi.dto.account.request;

import jakarta.validation.constraints.*;
import kh.edu.istad.bankingapi.utils.Currency;

import java.math.BigDecimal;

public record CreateAccountRequest(

        String accountNumber,

        @NotBlank(message = "Account name is required")
        @Size(max = 120, message = "Account name cannot be more than 120 characters")
        String accountName,

        @NotNull(message = "Currency is required")
        Currency currency,

        @NotBlank(message = "Phone number is required")
        @Size(min = 9, max = 12, message = "Phone number must be 9 to 12 digits")
        @Pattern(regexp = "^[0-9]{9,12}$", message = "Phone number must contain only digits")
        String phoneNumber,

        @NotNull(message = "Balance is required")
        BigDecimal balance,

        @NotBlank(message = "Account type is required")
        String accountType
) {
}
