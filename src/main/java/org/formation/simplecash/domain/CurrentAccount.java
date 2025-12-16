package org.formation.simplecash.domain;

import java.math.BigDecimal;

public class CurrentAccount extends Account {
    private BigDecimal overdraft = BigDecimal.valueOf(1000);

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(String accountName, Client client) {
        super(accountName, client);
        this.overdraft = BigDecimal.valueOf(1000);
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }
}
