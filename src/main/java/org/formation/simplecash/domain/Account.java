package org.formation.simplecash.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Account {
    private Long id;
    private String accountNumber;
    private BigDecimal balance = BigDecimal.valueOf(0);
    private LocalDateTime creationDate = LocalDateTime.now();
    private Client client;

    public Account() {
    }

    public Account(String accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.client = client;
        this.creationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
