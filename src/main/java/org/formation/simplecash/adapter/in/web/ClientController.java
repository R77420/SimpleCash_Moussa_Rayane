package org.formation.simplecash.adapter.in.web;

import org.formation.simplecash.adapter.in.web.dto.AccountDTO;
import org.formation.simplecash.adapter.in.web.dto.ClientDTO;
import org.formation.simplecash.adapter.in.web.dto.WebMapper;
import org.formation.simplecash.application.port.in.ClientUseCase;
import org.formation.simplecash.domain.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientUseCase clientUseCase;
    private final WebMapper webMapper;

    public ClientController(ClientUseCase clientUseCase, WebMapper webMapper) {
        this.clientUseCase = clientUseCase;
        this.webMapper = webMapper;
    }

    @PostMapping("/advisors/{advisorId}/clients")
    public ClientDTO createClient(@PathVariable Long advisorId, @RequestBody ClientDTO clientDTO) {
        Client client = webMapper.toDomain(clientDTO);
        return webMapper.toDTO(clientUseCase.create(advisorId, client));
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return webMapper.toDTO(clientUseCase.getClient(id));
    }

    @PutMapping("/clients/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Client client = webMapper.toDomain(clientDTO);
        return webMapper.toDTO(clientUseCase.updateClient(id, client));
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientUseCase.delete(id);
    }

    @GetMapping("/advisors/{advisorId}/clients")
    public List<ClientDTO> getClients(@PathVariable Long advisorId) {
        return webMapper.toClientDTOList(clientUseCase.getClientsByAdvisor(advisorId));
    }

    @GetMapping("/clients/{id}/accounts")
    public List<AccountDTO> getAccounts(@PathVariable Long id) {
        return webMapper.toAccountDTOList(clientUseCase.getClient(id).getAccounts());
    }
}
