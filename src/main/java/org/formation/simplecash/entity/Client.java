package org.formation.simplecash.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {
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
    private Advisor advisor;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Client(String name, String surname, String adress, String code, String city, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.code = code;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}
