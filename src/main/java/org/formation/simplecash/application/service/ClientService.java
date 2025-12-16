package org.formation.simplecash.application.service;

import org.formation.simplecash.application.port.in.ClientUseCase;
import org.formation.simplecash.application.port.out.AccountPort;
import org.formation.simplecash.application.port.out.AdvisorPort;
import org.formation.simplecash.application.port.out.ClientPort;
import org.formation.simplecash.domain.Advisor;
import org.formation.simplecash.domain.Client;
import org.formation.simplecash.domain.Account;
import jakarta.transaction.Transactional;

import java.util.List;

public class ClientService implements ClientUseCase {

    private final ClientPort clientPort;
    private final AdvisorPort advisorPort;
    private final AccountPort accountPort;

    public ClientService(ClientPort clientPort, AdvisorPort advisorPort, AccountPort accountPort) {
        this.clientPort = clientPort;
        this.advisorPort = advisorPort;
        this.accountPort = accountPort;
    }

    @Transactional
    @Override
    public Client create(Long advisorId, Client client) {
        Advisor advisor = advisorPort.findById(advisorId)
                .orElseThrow(() -> new RuntimeException("Advisor did not exist"));
        if (advisor.getClients() != null && advisor.getClients().size() >= 10)
            throw new RuntimeException("Advisor have 10 clients");

        client.setAdvisor(advisor);
        if (advisor.getClients() != null) {
            advisor.getClients().add(client);
        }
        // Save client
        return clientPort.save(client);
    }

    @Override
    public Client getClient(Long id) {
        return clientPort.findById(id).orElseThrow(() -> new RuntimeException("Client did not exist"));
    }

    @Transactional
    @Override
    public Client updateClient(Long id, Client client) {
        Client oldClient = getClient(id);
        oldClient.setName(client.getName());
        oldClient.setSurname(client.getSurname());
        oldClient.setAdress(client.getAdress());
        oldClient.setCity(client.getCity());
        oldClient.setPhoneNumber(client.getPhoneNumber());
        return clientPort.save(oldClient);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Client client = getClient(id);
        List<Account> accounts = accountPort.findByClientId(client.getId());
        accountPort.deleteAll(accounts);

        if (client.getAdvisor() != null && client.getAdvisor().getClients() != null) {
            client.getAdvisor().getClients().remove(client);
        }
        clientPort.delete(client);
    }

    @Override
    public List<Client> getClientsByAdvisor(Long advisorId) {
        return clientPort.findByAdvisorId(advisorId);
    }
}
