package kh.edu.istad.bankingapi.mapper;

import kh.edu.istad.bankingapi.domain.Customer;
import kh.edu.istad.bankingapi.dto.customer.CreateCustomerRequest;
import kh.edu.istad.bankingapi.dto.customer.CustomerResponse;
import kh.edu.istad.bankingapi.dto.customer.UpdateCustomerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
//    DTO -> Model
//    Model -> DTO
//    What is source data? (parameter)
//    what is target data? (return type)

    CustomerResponse fromCustomerToCustomerResponse(Customer customer);

    Customer fromCreateCustomerRequestToCustomer(CreateCustomerRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);
}
