package org.formation.simplecash.repository;

import org.formation.simplecash.entity.Account;
import org.formation.simplecash.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByClientId(Long clientId);


}
