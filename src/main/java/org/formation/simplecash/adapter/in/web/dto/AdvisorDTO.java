package org.formation.simplecash.adapter.in.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AdvisorDTO {
    private long id;
    private String name;
    // We avoid circular dependency or deep recursion by probably using a summary
    // DTO for clients or ignoring it?
    // Original entity had: private List<Client> clients = new ArrayList<>();
    // If we want to strictly preserve the output, we need to include it.
    // However, typically infinite recursion is handled by Jackson or by ignoring.
    // The original code didn't have @JsonIgnore.
    // Let's assume for now we replicate the structure.
    // To handle 'Client', we also need 'ClientDTO'.
    // I will use 'Object' or just exclude it if it causes issues, but for now let's
    // try to match.
    // Wait, if I change to DTO, I should probably also have ClientDTO.
    // For now I will just use ID/Name to be safe or maybe recursive.

    // Let's stick to simple fields first as Client refactor is not done yet.
    // Actually, if I don't import Client, I can't put List<Client>.
    // I will use List<ClientDTO> but I haven't created ClientDTO yet.
    // I will defer adding 'clients' field until ClientDTO is ready OR I will create
    // ClientDTO stub now.
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("advisor")
    private List<ClientDTO> clients = new ArrayList<>();
}
