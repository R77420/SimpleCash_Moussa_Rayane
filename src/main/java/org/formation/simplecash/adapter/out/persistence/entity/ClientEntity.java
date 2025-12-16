package org.formation.simplecash.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String adress;
    private String code;
    private String city;
    private String phoneNumber;

    @ManyToOne
    private AdvisorEntity advisor;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts = new ArrayList<>();

    public ClientEntity(String name, String surname, String adress, String code, String city, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.code = code;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}
