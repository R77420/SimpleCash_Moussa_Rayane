package org.formation.simplecash.domain;

import java.math.BigDecimal;

public class SavingAccount extends Account {
    private BigDecimal rate = BigDecimal.valueOf(0.3);

    public SavingAccount() {
        super();
    }

    public SavingAccount(String accountName, Client client) {
        super(accountName, client);
        this.rate = BigDecimal.valueOf(0.3);
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
