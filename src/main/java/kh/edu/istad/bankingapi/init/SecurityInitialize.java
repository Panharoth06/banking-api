package kh.edu.istad.bankingapi.init;

import jakarta.annotation.PostConstruct;
import kh.edu.istad.bankingapi.domain.Role;
import kh.edu.istad.bankingapi.domain.User;
import kh.edu.istad.bankingapi.repository.RoleRepository;
import kh.edu.istad.bankingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityInitialize {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {

        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            Role userRole = new  Role();
            userRole.setName("USER");

            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            Role staffRole = new Role();
            staffRole.setName("STAFF");

            Role customerRole = new Role();
            customerRole.setName("CUSTOMER");

            roleRepository.saveAll(List.of(userRole, adminRole, staffRole, customerRole));


            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("pwd@123");
            admin.setRoles(Set.of(adminRole, userRole));

            User staff = new User();
            staff.setUsername("staff");
            staff.setPassword("pwd@123");
            staff.setRoles(Set.of(staffRole, userRole));

            User customer = new User();
            customer.setUsername("customer");
            customer.setPassword("pwd@123");
            customer.setRoles(Set.of(customerRole, userRole));

            userRepository.saveAll(List.of(admin, staff, customer));
        }
    }

}
