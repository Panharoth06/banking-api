package kh.edu.istad.bankingapi.dto.customer;

public record UpdateCustomerRequest(
        String fullName,
        String remark,
        String gender
) {
}
