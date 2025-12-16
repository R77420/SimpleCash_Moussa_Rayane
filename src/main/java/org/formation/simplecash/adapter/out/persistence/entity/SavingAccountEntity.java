package org.formation.simplecash.adapter.out.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@DiscriminatorValue("SAVING")
@NoArgsConstructor
public class SavingAccountEntity extends AccountEntity {
    private BigDecimal rate = BigDecimal.valueOf(0.3);

    public SavingAccountEntity(String accountName, ClientEntity client) {
        super(accountName, client);
        this.rate = BigDecimal.valueOf(0.3);
    }
}
