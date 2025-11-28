package org.formation.simplecash.controller;

import org.formation.simplecash.entity.Account;
import org.formation.simplecash.entity.Client;
import org.formation.simplecash.service.AccountService;
import org.formation.simplecash.service.AdvisorService;
import org.formation.simplecash.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/advisors/{advisorId}/clients")
    public Client createClient(@PathVariable Long advisorId, @RequestBody Client client) {
        return clientService.create(advisorId, client);
    }

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }

    @GetMapping("/advisors/{advisorId}/clients")
    public List<Client> getClients(@PathVariable Long advisorId) {
        return clientService.getClientsByAdvisor(advisorId);
    }

    @GetMapping("/clients/{id}/accounts")
    public List<Account> getAccounts(@PathVariable Long id) {
        return clientService.getClient(id).getAccounts();
    }
}
