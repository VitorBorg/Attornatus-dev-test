package com.vitorBorg.Attornatus.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PersonModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idPerson;
    private String namePerson;
    @Nullable
    private Long idPrincipalAddress;
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    //private LocalDate birthDatePessoa;
    private String birthDatePerson;

    public PersonModel(Long idPerson, String namePerson, String birthDatePerson, Long idPrincipalAddress) {
        this.idPerson = idPerson;
        this.namePerson = namePerson;
        this.birthDatePerson = birthDatePerson;
        this.idPrincipalAddress = idPrincipalAddress;
    }

    public PersonModel(){}

    public Long getIdPerson() {
        return idPerson;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public String getBirthDatePerson() {
        return birthDatePerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public void setBirthDatePerson(String birthDatePerson) {
        this.birthDatePerson = birthDatePerson;
    }

    public Long getIdPrincipalAddress() {
        return idPrincipalAddress;
    }

    public void setIdPrincipalAddress(Long idPrincipalAddress) {
        this.idPrincipalAddress = idPrincipalAddress;
    }
}
