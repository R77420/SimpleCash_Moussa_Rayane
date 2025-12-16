package org.formation.simplecash.adapter.out.persistence;

import org.formation.simplecash.adapter.out.persistence.entity.AdvisorEntity;
import org.formation.simplecash.adapter.out.persistence.mapper.AdvisorMapper;
import org.formation.simplecash.adapter.out.persistence.repository.AdvisorRepository;
import org.formation.simplecash.application.port.out.AdvisorPort;
import org.formation.simplecash.domain.Advisor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdvisorPersistenceAdapter implements AdvisorPort {

    private final AdvisorRepository advisorRepository;
    private final AdvisorMapper advisorMapper;

    public AdvisorPersistenceAdapter(AdvisorRepository advisorRepository, AdvisorMapper advisorMapper) {
        this.advisorRepository = advisorRepository;
        this.advisorMapper = advisorMapper;
    }

    @Override
    public Advisor save(Advisor advisor) {
        AdvisorEntity entity = advisorMapper.toEntity(advisor);
        AdvisorEntity savedEntity = advisorRepository.save(entity);
        return advisorMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Advisor> findById(Long id) {
        return advisorRepository.findById(id).map(advisorMapper::toDomain);
    }

    @Override
    public void delete(Advisor advisor) {
        AdvisorEntity entity = advisorMapper.toEntity(advisor);
        advisorRepository.delete(entity);
    }

    @Override
    public List<Advisor> findAll() {
        return advisorMapper.toDomainList(advisorRepository.findAll());
    }
}
