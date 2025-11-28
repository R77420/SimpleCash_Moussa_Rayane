package org.formation.simplecash.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@DiscriminatorColumn(name = "account_type")
@NoArgsConstructor
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private BigDecimal balance = BigDecimal.valueOf(0);

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private Client client;

    public Account(String accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.client = client;
        this.creationDate = LocalDateTime.now();
    }
}
