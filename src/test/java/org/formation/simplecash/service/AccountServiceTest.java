package org.formation.simplecash.service;

import org.formation.simplecash.entity.Account;
import org.formation.simplecash.entity.CurrentAccount;
import org.formation.simplecash.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testGetByNumber() {
        Account acc = new CurrentAccount();
        acc.setAccountNumber("ACC123");

        when(accountRepository.findByAccountNumber("ACC123"))
                .thenReturn(Optional.of(acc));

        Account result = accountService.getByAccountNumber("ACC123");

        assertEquals("ACC123", result.getAccountNumber());
    }

    @Test
    void testAddMoney() {
        Account acc = new CurrentAccount();
        acc.setAccountNumber("ACC123");
        acc.setBalance(BigDecimal.valueOf(100));

        when(accountRepository.findByAccountNumber("ACC123"))
                .thenReturn(Optional.of(acc));

        when(accountRepository.save(acc))
                .thenReturn(acc);

        Account updated = accountService.addMoney("ACC123", BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(150), updated.getBalance());
    }

    @Test
    void testRemoveMoney_CurrentAccount_Authorized() {
        CurrentAccount acc = new CurrentAccount();
        acc.setAccountNumber("ACC999");
        acc.setBalance(BigDecimal.valueOf(500));
        acc.setOverdraft(BigDecimal.valueOf(1000)); // limite = -1000

        when(accountRepository.findByAccountNumber("ACC999"))
                .thenReturn(Optional.of(acc));

        when(accountRepository.save(acc))
                .thenReturn(acc);

        Account updated = accountService.removeMoney("ACC999", BigDecimal.valueOf(200));

        assertEquals(BigDecimal.valueOf(300), updated.getBalance());
    }

}
