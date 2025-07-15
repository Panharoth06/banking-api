package kh.edu.istad.bankingapi.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(

        @NotBlank(message = "Full name is required")
        @Size(max = 120, message = "Full name cannot be more than 120 characters")
        String fullName,

        @NotBlank(message = "Gender is required")
        @Size(max = 10, message = "Gender value is not correct")
        String gender,

        @NotBlank(message = "Email is required")
        @Email(message = "Email is not correct")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^(0[1-9]\\d{7,8}|\\+855[1-9]\\d{7})$",
                message = "Phone number must be a valid Cambodian number"
        )
        String phoneNumber,

        @Size(max = 255, message = "Remark cannot exceed 255 characters")
        String remark,

        @NotBlank(message = "Segment name is required")
        @Size(max = 100, message = "Segment name cannot exceed 100 characters")
        String segmentName,

        @NotBlank(message = "National Card ID is required")
        @Pattern(
                regexp = "^(\\d{9}|[NF]\\d{8})$",
                message = "National Card ID must be 9 digits or start with N/F followed by 8 digits"
        )
        String nationCardId
) {
}
