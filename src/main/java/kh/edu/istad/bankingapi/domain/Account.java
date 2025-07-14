package kh.edu.istad.bankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String actName;

    @Column(nullable = false, unique = true)
    private String actNo;

    private BigDecimal balance;

    @Column(nullable = false, precision = 5)
    private BigDecimal overLimit;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "acc_type", nullable = false)
    private AccountType accountType;

}
