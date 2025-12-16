package org.formation.simplecash.adapter.out.persistence.repository;

import org.formation.simplecash.adapter.out.persistence.entity.CurrentAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccountEntity, Long> {
}
