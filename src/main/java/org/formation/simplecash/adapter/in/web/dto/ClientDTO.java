package org.formation.simplecash.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClientDTO {
    private long id;
    private String name;
    private String surname;
    private String adress;
    private String code;
    private String city;
    private String phoneNumber;

    @JsonIgnoreProperties("clients")
    private AdvisorDTO advisor;

    private List<AccountDTO> accounts = new ArrayList<>();
}
