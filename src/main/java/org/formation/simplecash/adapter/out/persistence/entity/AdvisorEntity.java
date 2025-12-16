package org.formation.simplecash.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advisor")
@Data
@NoArgsConstructor
public class AdvisorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "advisor")
    private List<ClientEntity> clients = new ArrayList<>();

    public AdvisorEntity(String name) {
        this.name = name;
    }
}
