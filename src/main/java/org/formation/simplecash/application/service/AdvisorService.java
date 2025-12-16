package org.formation.simplecash.application.service;

import org.formation.simplecash.application.port.in.AdvisorUseCase;
import org.formation.simplecash.application.port.out.AdvisorPort;
import org.formation.simplecash.domain.Advisor;
import jakarta.transaction.Transactional;

import java.util.List;

public class AdvisorService implements AdvisorUseCase {

    private final AdvisorPort advisorPort;

    public AdvisorService(AdvisorPort advisorPort) {
        this.advisorPort = advisorPort;
    }

    @Transactional
    @Override
    public Advisor create(Advisor advisor) {
        return advisorPort.save(advisor);
    }

    @Override
    public Advisor getAdvisor(Long id) {
        return advisorPort.findById(id).orElseThrow(() -> new RuntimeException("Advisor did not exist"));
    }

    @Transactional
    @Override
    public Advisor update(Advisor advisor) {
        Advisor oldAdvisor = getAdvisor(advisor.getId());
        oldAdvisor.setName(advisor.getName());
        return advisorPort.save(oldAdvisor);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Advisor advisor = getAdvisor(id);
        if (advisor.getClients() != null && !advisor.getClients().isEmpty()) {
            throw new RuntimeException("Advisor still have clients");
        }
        advisorPort.delete(advisor);
    }

    @Override
    public List<Advisor> getAll() {
        return advisorPort.findAll();
    }
}
