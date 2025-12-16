package org.formation.simplecash.adapter.out.persistence.repository;

import org.formation.simplecash.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    java.util.List<ClientEntity> findByAdvisorId(Long advisorId);
}
