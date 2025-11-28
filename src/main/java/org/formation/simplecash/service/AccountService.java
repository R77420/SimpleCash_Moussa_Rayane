package org.formation.simplecash.service;

import org.springframework.transaction.annotation.Transactional;
import org.formation.simplecash.entity.Account;
import org.formation.simplecash.entity.CurrentAccount;
import org.formation.simplecash.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
    }

    @Transactional
    public Account addMoney(String accountNumber, BigDecimal money) {
        Account account = getByAccountNumber(accountNumber);
        account.setBalance(account.getBalance().add(money));
        return accountRepository.save(account);
    }

    @Transactional
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
        return accountRepository.save(account);
    }

    public List<Account> getAccountsOfClient(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }
}
