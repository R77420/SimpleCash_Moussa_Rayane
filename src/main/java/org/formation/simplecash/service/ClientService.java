package org.formation.simplecash.service;


import org.formation.simplecash.entity.Account;
import org.formation.simplecash.entity.Advisor;
import org.formation.simplecash.entity.Client;
import org.formation.simplecash.repository.AccountRepository;
import org.formation.simplecash.repository.AdvisorRepository;
import org.formation.simplecash.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    private final AccountService accountService;
    private final ClientRepository clientRepository;
    private final AdvisorRepository advisorRepository;
    private final AccountRepository accountRepository;

    public ClientService(AccountService accountService, ClientRepository clientRepository, AdvisorRepository advisorRepository, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.clientRepository = clientRepository;
        this.advisorRepository = advisorRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Client create(Long advisorId, Client client) {
        Advisor advisor = advisorRepository.findById(advisorId).orElseThrow(() -> new RuntimeException("Advisor did not exist"));
        if (advisor.getClients().size() >= 10)
            throw new RuntimeException("Advisor have 10 clients");
        client.setAdvisor(advisor);
        advisor.getClients().add(client);
        return clientRepository.save(client);
    }

    public Client getClient(Long id)
    {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client did not exist"));
    }

    @Transactional
    public Client updateClient(Long id, Client client) {
        Client oldClient = getClient(id);
        oldClient.setName(client.getName());
        oldClient.setSurname(client.getSurname());
        oldClient.setAdress(client.getAdress());
        oldClient.setCity(client.getCity());
        oldClient.setPhoneNumber(client.getPhoneNumber());
        return clientRepository.save(oldClient);
    }

    @Transactional
    public void delete(Long id) {
        Client client = getClient(id);
        List<Account> accounts = accountRepository.findByClientId(client.getId());
        accountRepository.deleteAll(accounts);
        if (client.getAdvisor() != null)
            client.getAdvisor().getClients().remove(client);
        clientRepository.delete(client);
    }

    @Transactional
    public List<Client> getClientsByAdvisor(Long advisorId) {
        return clientRepository.findByAdvisorId(advisorId);
    }
}
