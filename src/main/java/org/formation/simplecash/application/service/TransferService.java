package org.formation.simplecash.application.service;

import org.formation.simplecash.application.port.in.AccountUseCase;
import org.formation.simplecash.application.port.in.TransferUseCase;
import org.formation.simplecash.domain.Account;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

public class TransferService implements TransferUseCase {

    private final AccountUseCase accountUseCase;

    public TransferService(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    @Transactional
    @Override
    public void transfer(String accountNumber1, String accountNumber2, BigDecimal amount) {
        if (accountNumber1.equals(accountNumber2))
            throw new IllegalArgumentException("You can't send money to yourself");
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || amount == null) {
            throw new IllegalArgumentException("Amount can't be negative or null");
        }

        // We checks if the accounts really exist (by calling getByAccountNumber which
        // throws if not found)
        Account source = accountUseCase.getByAccountNumber(accountNumber1);
        Account destination = accountUseCase.getByAccountNumber(accountNumber2);

        accountUseCase.removeMoney(accountNumber1, amount);
        accountUseCase.addMoney(accountNumber2, amount);
    }
}
