package kh.edu.istad.bankingapi.dto.customer;

public record CreateCustomerRequest(
        String fullName,
        String gender,
        String email,
        String phoneNumber,
        String remark,
        String segmentName,
        String nationCardId
) {
}
