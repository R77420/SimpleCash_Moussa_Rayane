package org.formation.simplecash.domain;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private long id;
    private String name;
    private String surname;
    private String adress;
    private String code;
    private String city;
    private String phoneNumber;
    private Advisor advisor;
    private List<Account> accounts = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String surname, String adress, String code, String city, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.code = code;
        this.city = city;
        this.phoneNumber = phoneNumber;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
