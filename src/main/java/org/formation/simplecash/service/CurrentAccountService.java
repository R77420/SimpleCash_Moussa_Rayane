package org.formation.simplecash.service;

import org.formation.simplecash.entity.Account;
import org.formation.simplecash.entity.Client;
import org.formation.simplecash.entity.CurrentAccount;
import org.formation.simplecash.repository.AccountRepository;
import org.formation.simplecash.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CurrentAccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public CurrentAccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public CurrentAccount createAccount(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client does not exist"));
        CurrentAccount account = new CurrentAccount();
        account.setClient(client);
        account.setBalance(BigDecimal.ZERO);
        account.setOverdraft(BigDecimal.valueOf(1000));
        account.setAccountNumber("CC-" + clientId + "-" + System.currentTimeMillis());
        return accountRepository.save(account);
    }

    public CurrentAccount getByNumber(String accountNumber) {
        return (CurrentAccount) accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account does not exist"));
    }

    public CurrentAccount getById(Long id) {
        return (CurrentAccount) accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
    }





}
