package kh.edu.istad.bankingapi.repository;

import kh.edu.istad.bankingapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByIsDeletedFalse();

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Customer> findCustomerByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    @Modifying
    @Query(value =
    """
    UPDATE Customer cus
    SET cus.isDeleted = true
    WHERE cus.phoneNumber = ?1
    """)
    void disableAccountByPhoneNumber(String phoneNumber);

    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);


}
