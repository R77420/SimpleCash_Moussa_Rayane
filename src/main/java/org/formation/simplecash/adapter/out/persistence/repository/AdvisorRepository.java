package org.formation.simplecash.adapter.out.persistence.repository;

import org.formation.simplecash.adapter.out.persistence.entity.AdvisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepository extends JpaRepository<AdvisorEntity, Long> {
}
