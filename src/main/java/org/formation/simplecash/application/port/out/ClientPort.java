package org.formation.simplecash.application.port.out;

import org.formation.simplecash.domain.Client;
import java.util.List;
import java.util.Optional;

public interface ClientPort {
    Client save(Client client);

    Optional<Client> findById(Long id);

    void delete(Client client);

    List<Client> findByAdvisorId(Long advisorId);
}
