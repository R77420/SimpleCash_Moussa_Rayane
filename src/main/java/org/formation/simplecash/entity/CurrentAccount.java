package org.formation.simplecash.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account{
    private BigDecimal overdraft = BigDecimal.valueOf(1000);
    public CurrentAccount(String accountName, Client client) {
        super(accountName, client);
        this.overdraft = BigDecimal.valueOf(1000);
    }

}
