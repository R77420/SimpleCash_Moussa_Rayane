package org.formation.simplecash.service;

import org.formation.simplecash.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {
    private final AccountService accountService;

    public TransferService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Transactional
    public void transfer(String accountNumber1, String accountNumber2, BigDecimal amount) {
        if (accountNumber1.equals(accountNumber2))
            throw new IllegalArgumentException("You can't send money to yourself");
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || amount == null){
            throw new IllegalArgumentException("Amount can't be negative or null");
        }

        //We checks if the accounts really exist
        Account source = accountService.getByAccountNumber(accountNumber1);
        Account destination = accountService.getByAccountNumber(accountNumber2);

        accountService.removeMoney(accountNumber1, amount);
        accountService.addMoney(accountNumber2, amount);
    }
}
