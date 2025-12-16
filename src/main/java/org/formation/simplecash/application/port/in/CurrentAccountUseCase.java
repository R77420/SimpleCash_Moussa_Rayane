package org.formation.simplecash.application.port.in;

import org.formation.simplecash.domain.CurrentAccount;

public interface CurrentAccountUseCase {
    CurrentAccount createAccount(Long clientId);

    CurrentAccount getByNumber(String accountNumber);

    CurrentAccount getById(Long id);
}
