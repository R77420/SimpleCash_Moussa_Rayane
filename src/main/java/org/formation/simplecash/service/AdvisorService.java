package org.formation.simplecash.service;

import org.formation.simplecash.entity.Advisor;
import org.formation.simplecash.entity.Client;
import org.formation.simplecash.repository.AdvisorRepository;
import org.formation.simplecash.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvisorService {
    private final AdvisorRepository advisorRepository;
    private final ClientRepository clientRepository;

    public AdvisorService(AdvisorRepository advisorRepository, ClientRepository clientRepository) {
        this.advisorRepository = advisorRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Advisor create(Advisor advisor) {
        return advisorRepository.save(advisor);
    }

    public Advisor getAdvisor(Long id) {
        return advisorRepository.findById(id).orElseThrow( () -> new RuntimeException("Advisor did not exist"));
    }

    @Transactional
    public Advisor update(Advisor advisor) {
        Advisor oldAdvisor = getAdvisor(advisor.getId());
        oldAdvisor.setName(advisor.getName());
        return advisorRepository.save(oldAdvisor);
    }

    @Transactional
    public void delete(Long id) {
        Advisor advisor = getAdvisor(id);
        if (!advisor.getClients().isEmpty()) {
            throw new RuntimeException("Advisor still have clients");
        }
        advisorRepository.delete(advisor);
    }

    public List<Advisor> getAll() {
        return advisorRepository.findAll();
    }
}
