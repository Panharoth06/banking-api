package kh.edu.istad.bankingapi.dto.account.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateAccountRequest(

        @NotBlank(message = "Account name is required")
        @Size(max = 120, message = "Account name cannot be more than 120 characters")
        String accountName,

        @NotBlank(message = "Phone number is required")
        @Size(min = 9, max = 12, message = "Phone number must be 9 to 12 digits")
        @Pattern(regexp = "^[0-9]{9,12}$", message = "Phone number must contain only digits")
        String phoneNumber,

        @DecimalMin(value = "5.0", message = "Over limit cannot be lower than 5")
        @NotNull(message = "Over limit is required")
        BigDecimal overLimit,

        @NotBlank(message = "Account type is required")
        String accountType
) {
}
