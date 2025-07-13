package kh.edu.istad.bankingapi.dto.account.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateAccountRequest(

        @DecimalMin(value = "5.0", message = "Over limit cannot be lower than 5")
        @NotNull(message = "Over limit is required")
        BigDecimal overLimit,

        @NotBlank(message = "Account name is required")
        @Size(max = 120, message = "Account name cannot be more than 120 characters")
        String accountName
) { }
