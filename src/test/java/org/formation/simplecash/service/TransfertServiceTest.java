package org.formation.simplecash.service;

import org.formation.simplecash.application.port.in.AccountUseCase;
import org.formation.simplecash.application.service.TransferService;
import org.formation.simplecash.domain.CurrentAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private AccountUseCase accountUseCase;

    @InjectMocks
    private TransferService transferService;

    @Test
    void testTransferSuccess() {
        // GIVEN
        CurrentAccount source = new CurrentAccount();
        source.setAccountNumber("SRC");
        source.setBalance(BigDecimal.valueOf(500));

        CurrentAccount destination = new CurrentAccount();
        destination.setAccountNumber("DEST");
        destination.setBalance(BigDecimal.valueOf(200));

        when(accountUseCase.getByAccountNumber("SRC")).thenReturn(source);
        when(accountUseCase.getByAccountNumber("DEST")).thenReturn(destination);

        // WHEN
        transferService.transfer("SRC", "DEST", BigDecimal.valueOf(100));

        // THEN
        verify(accountUseCase, times(1)).removeMoney("SRC", BigDecimal.valueOf(100));
        verify(accountUseCase, times(1)).addMoney("DEST", BigDecimal.valueOf(100));
    }

    @Test
    void testTransferSameAccount() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> transferService.transfer("ACC", "ACC", BigDecimal.valueOf(100)));

        assertEquals("You can't send money to yourself", ex.getMessage());
    }

    @Test
    void testTransferInvalidAmount() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> transferService.transfer("SRC", "DEST", BigDecimal.valueOf(-50)));

        assertEquals("Amount can't be negative or null", ex.getMessage());
    }

    @Test
    void testTransferInsufficientFunds() {
        CurrentAccount source = new CurrentAccount();
        source.setAccountNumber("SRC");
        source.setBalance(BigDecimal.valueOf(50));
        source.setOverdraft(BigDecimal.valueOf(1000));

        CurrentAccount destination = new CurrentAccount();
        destination.setAccountNumber("DEST");
        destination.setBalance(BigDecimal.valueOf(200));

        when(accountUseCase.getByAccountNumber("SRC")).thenReturn(source);
        when(accountUseCase.getByAccountNumber("DEST")).thenReturn(destination);

        // Simule une exception de removeMoney()
        doThrow(new RuntimeException("limit of balance exceded"))
                .when(accountUseCase)
                .removeMoney("SRC", BigDecimal.valueOf(500));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> transferService.transfer("SRC", "DEST", BigDecimal.valueOf(500)));

        assertEquals("limit of balance exceded", ex.getMessage());
    }
}
