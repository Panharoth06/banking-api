package kh.edu.istad.bankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(length = 100)
    private String address;
    @Column(length = 50)
    private String cityOrProvince;
    @Column(length = 50)
    private String country;
    @Column(length = 50)
    private String zipCode;

    @Column(length = 50)
    private String employmentType;
    @Column(length = 50)
    private String position;
    @Column(length = 50)
    private String companyName;
    @Column(length = 50)
    private String mainSourceOfIncome;
    @Column(length = 50)
    private BigDecimal monthlyIncomeRange;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private KYC kyc;

    @ManyToOne
    private CustomerSegment customerSegment;

}
