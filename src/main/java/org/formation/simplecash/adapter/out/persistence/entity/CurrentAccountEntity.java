package org.formation.simplecash.adapter.out.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("CURRENT")
public class CurrentAccountEntity extends AccountEntity {
    private BigDecimal overdraft = BigDecimal.valueOf(1000);

    public CurrentAccountEntity(String accountName, ClientEntity client) {
        super(accountName, client);
        this.overdraft = BigDecimal.valueOf(1000);
    }
}
