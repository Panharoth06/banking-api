package kh.edu.istad.bankingapi.dto.customer;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String fullName,
        String email,
        String phoneNumber
) {
}
