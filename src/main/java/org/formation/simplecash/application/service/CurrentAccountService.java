package org.formation.simplecash.application.service;

import org.formation.simplecash.application.port.in.CurrentAccountUseCase;
import org.formation.simplecash.application.port.out.AccountPort;
import org.formation.simplecash.application.port.out.ClientPort;
import org.formation.simplecash.domain.Client;
import org.formation.simplecash.domain.CurrentAccount;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

public class CurrentAccountService implements CurrentAccountUseCase {
    private final AccountPort accountPort;
    private final ClientPort clientPort;

    public CurrentAccountService(AccountPort accountPort, ClientPort clientPort) {
        this.accountPort = accountPort;
        this.clientPort = clientPort;
    }

    @Transactional
    @Override
    public CurrentAccount createAccount(Long clientId) {
        Client client = clientPort.findById(clientId).orElseThrow(() -> new RuntimeException("Client does not exist"));
        CurrentAccount account = new CurrentAccount();
        account.setClient(client);
        account.setBalance(BigDecimal.ZERO);
        account.setOverdraft(BigDecimal.valueOf(1000));
        account.setAccountNumber("CC-" + clientId + "-" + System.currentTimeMillis());
        return (CurrentAccount) accountPort.save(account);
    }

    @Override
    public CurrentAccount getByNumber(String accountNumber) {
        return (CurrentAccount) accountPort.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
    }

    @Override
    public CurrentAccount getById(Long id) {
        return (CurrentAccount) accountPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
    }
}
