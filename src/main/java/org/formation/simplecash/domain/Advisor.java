package org.formation.simplecash.domain;

import java.util.ArrayList;
import java.util.List;

public class Advisor {
    private long id;
    private String name;
    private List<Client> clients = new ArrayList<>();

    public Advisor() {
    }

    public Advisor(String name) {
        this.name = name;
    }

    public Advisor(long id, String name, List<Client> clients) {
        this.id = id;
        this.name = name;
        this.clients = clients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
