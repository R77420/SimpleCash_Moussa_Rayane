package org.formation.simplecash.application.port.out;

import org.formation.simplecash.domain.Account;
import java.util.List;
import java.util.Optional;

public interface AccountPort {
    Account save(Account account);

    Optional<Account> findById(Long id);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByClientId(Long clientId);

    void delete(Account account);

    void deleteAll(List<Account> accounts);
}
