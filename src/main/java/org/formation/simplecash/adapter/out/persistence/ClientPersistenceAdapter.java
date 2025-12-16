package org.formation.simplecash.adapter.out.persistence;

import org.formation.simplecash.adapter.out.persistence.entity.ClientEntity;
import org.formation.simplecash.adapter.out.persistence.mapper.ClientMapper;
import org.formation.simplecash.adapter.out.persistence.repository.ClientRepository;
import org.formation.simplecash.application.port.out.ClientPort;
import org.formation.simplecash.domain.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientPersistenceAdapter implements ClientPort {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientPersistenceAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = clientMapper.toEntity(client);
        // Ensure that if we have an Advisor attached, it is handled by JPA (usually
        // managed by ID or cascade)
        ClientEntity savedEntity = clientRepository.save(entity);
        return clientMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDomain);
    }

    @Override
    public void delete(Client client) {
        ClientEntity entity = clientMapper.toEntity(client);
        clientRepository.delete(entity);
    }

    @Override
    public List<Client> findByAdvisorId(Long advisorId) {
        return clientMapper.toDomainList(clientRepository.findByAdvisorId(advisorId));
    }
}
