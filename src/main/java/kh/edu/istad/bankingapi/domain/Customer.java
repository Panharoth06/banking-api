package kh.edu.istad.bankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(unique = true, length = 100)
    private String email;

    @Column(unique = true, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer")
    private KYC kyc;

    @ManyToOne
    private CustomerSegment customerSegment;

}
