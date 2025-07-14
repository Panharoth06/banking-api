package kh.edu.istad.bankingapi.service;

import kh.edu.istad.bankingapi.dto.customer.CreateCustomerRequest;
import kh.edu.istad.bankingapi.dto.customer.CustomerResponse;
import kh.edu.istad.bankingapi.dto.customer.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);

    List<CustomerResponse> findAll();

    /**
     * <p>
     *     This method is used to find a customer by their {@code phone number}
     * </p>
     * @param phoneNumber data from client
     * @return customer information {@code CustomerResponses}
     * @author Chheng Panharoth
     */
    CustomerResponse findCustomerByPhoneNumber(String phoneNumber);


    /**
     *
     * @param phoneNumber data from client
     * @param updateCustomerRequest data from client
     * @return updated customer - return type {@code CustomerResponse}
     */
    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

    void disableAccountByPhoneNumber(String phoneNumber);
}
