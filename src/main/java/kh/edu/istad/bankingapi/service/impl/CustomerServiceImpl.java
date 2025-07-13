package kh.edu.istad.bankingapi.service.impl;

import kh.edu.istad.bankingapi.domain.Customer;
import kh.edu.istad.bankingapi.dto.customer.CreateCustomerRequest;
import kh.edu.istad.bankingapi.dto.customer.CustomerResponse;
import kh.edu.istad.bankingapi.dto.customer.UpdateCustomerRequest;
import kh.edu.istad.bankingapi.mapper.CustomerMapper;
import kh.edu.istad.bankingapi.repository.CustomerRepository;
import kh.edu.istad.bankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper mapper;

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        if (customerRepository.existsByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        if  (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }

        Customer customer = mapper.fromCreateCustomerRequestToCustomer(createCustomerRequest);
        customer.setIsDeleted(false);
        customer = customerRepository.save(customer);

        return mapper.fromCustomerToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream().map(mapper::fromCustomerToCustomerResponse).toList();
    }

    @Override
    public CustomerResponse findCustomerByPhoneNumber(String phoneNumber) {
        return mapper.fromCustomerToCustomerResponse(
                customerRepository
                .findCustomerByPhoneNumber(phoneNumber)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found")
                )
        );
    }

    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerRepository.findCustomerByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found")
        );

        mapper.toCustomerPartially(updateCustomerRequest, customer);

        customer = customerRepository.save(customer);

        return mapper.fromCustomerToCustomerResponse(customer);
    }
}
