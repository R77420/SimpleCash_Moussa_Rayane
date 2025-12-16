package org.formation.simplecash.adapter.out.persistence.repository;

import org.formation.simplecash.adapter.out.persistence.entity.SavingAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccountEntity, Long> {
}
