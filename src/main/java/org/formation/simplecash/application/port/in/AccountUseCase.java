package org.formation.simplecash.application.port.in;

import org.formation.simplecash.domain.Account;
import java.math.BigDecimal;
import java.util.List;

public interface AccountUseCase {
    Account getByAccountNumber(String accountNumber);

    Account addMoney(String accountNumber, BigDecimal money);

    Account removeMoney(String accountNumber, BigDecimal money);

    List<Account> getAccountsOfClient(Long clientId);
}
