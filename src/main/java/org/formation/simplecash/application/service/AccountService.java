package org.formation.simplecash.application.service;

import org.formation.simplecash.application.port.in.AccountUseCase;
import org.formation.simplecash.application.port.out.AccountPort;
import org.formation.simplecash.domain.Account;
import org.formation.simplecash.domain.CurrentAccount;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

public class AccountService implements AccountUseCase {
    private final AccountPort accountPort;

    public AccountService(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountPort.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
    }

    @Transactional
    @Override
    public Account addMoney(String accountNumber, BigDecimal money) {
        Account account = getByAccountNumber(accountNumber);
        account.setBalance(account.getBalance().add(money));
        return accountPort.save(account);
    }

    @Transactional
    @Override
    public Account removeMoney(String accountNumber, BigDecimal money) {
        Account account = getByAccountNumber(accountNumber);
        BigDecimal newBalance = account.getBalance().subtract(money);

        if (account instanceof CurrentAccount currentAccount) {
            BigDecimal limit = currentAccount.getOverdraft().negate();
            if (newBalance.compareTo(limit) < 0) {
                throw new RuntimeException("limit of balance exceeded");
            }
        } else {
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("balance exceeded");
            }
        }
        account.setBalance(newBalance);
        return accountPort.save(account);
    }

    @Override
    public List<Account> getAccountsOfClient(Long clientId) {
        return accountPort.findByClientId(clientId);
    }
}
