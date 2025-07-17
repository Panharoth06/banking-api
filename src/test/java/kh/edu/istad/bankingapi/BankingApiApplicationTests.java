package kh.edu.istad.bankingapi;

import kh.edu.istad.bankingapi.repository.CustomerSegmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingApiApplicationTests {

    @Autowired
    private CustomerSegmentRepository customerSegmentRepository;

    @Test
    void testFetchType() {
        customerSegmentRepository.findAll().forEach(
                customerSegment -> {
                    System.out.println(customerSegment.getCustomers());
                }
        );
    }

}
