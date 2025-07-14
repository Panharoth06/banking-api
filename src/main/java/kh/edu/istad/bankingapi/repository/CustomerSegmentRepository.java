package kh.edu.istad.bankingapi.repository;

import kh.edu.istad.bankingapi.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer> {

    Optional<CustomerSegment> getCustomerSegmentBySegmentName(String segmentName);

}
