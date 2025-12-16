package org.formation.simplecash.adapter.out.persistence.repository;

import org.formation.simplecash.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByAccountNumber(String accountNumber);

    java.util.List<AccountEntity> findByClientId(Long clientId);
}
