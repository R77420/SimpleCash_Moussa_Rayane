package org.formation.simplecash.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@DiscriminatorValue("SAVING")
@NoArgsConstructor
public class SavingAccount extends Account {
    private BigDecimal rate = BigDecimal.valueOf(0.3);

    public SavingAccount(String accountName, Client client){
        super(accountName, client);
        this.rate = BigDecimal.valueOf(0.3);
    }
}
