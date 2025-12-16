package org.formation.simplecash.application.port.in;

import org.formation.simplecash.domain.Client;
import java.util.List;

public interface ClientUseCase {
    Client create(Long advisorId, Client client);

    Client getClient(Long id);

    Client updateClient(Long id, Client client);

    void delete(Long id);

    List<Client> getClientsByAdvisor(Long advisorId);
}
