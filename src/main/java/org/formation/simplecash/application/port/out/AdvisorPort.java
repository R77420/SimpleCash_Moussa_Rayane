package org.formation.simplecash.application.port.out;

import org.formation.simplecash.domain.Advisor;
import java.util.List;
import java.util.Optional;

public interface AdvisorPort {
    Advisor save(Advisor advisor);

    Optional<Advisor> findById(Long id);

    void delete(Advisor advisor);

    List<Advisor> findAll();
}
