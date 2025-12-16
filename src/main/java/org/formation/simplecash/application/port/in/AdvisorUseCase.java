package org.formation.simplecash.application.port.in;

import org.formation.simplecash.domain.Advisor;

import java.util.List;

public interface AdvisorUseCase {
    Advisor create(Advisor advisor);

    Advisor getAdvisor(Long id);

    Advisor update(Advisor advisor);

    void delete(Long id);

    List<Advisor> getAll();
}
