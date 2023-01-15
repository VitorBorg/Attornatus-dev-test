package com.vitorBorg.Attornatus.DTO;

import com.vitorBorg.Attornatus.model.AddressModel;
import jakarta.annotation.Nullable;
import jakarta.annotation.Nonnull;

import java.time.LocalDate;


public class PersonDTO {

    @Nonnull
    private Long idPerson;
    @Nonnull
    private String namePerson;
    @Nullable
    private AddressModel principalAddress;
    @Nonnull
    private LocalDate birthDatePerson;

    public PersonDTO(Long idPerson, String namePerson, LocalDate birthDatePerson, AddressModel principalAddress) {
        this.idPerson = idPerson;
        this.namePerson = namePerson;
        this.birthDatePerson = birthDatePerson;
        this.principalAddress = principalAddress;
    }

    public PersonDTO(){}

    public Long getIdPerson() {
        return idPerson;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public LocalDate getBirthDatePerson() {
        return birthDatePerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public void setBirthDatePerson(LocalDate birthDatePerson) {
        this.birthDatePerson = birthDatePerson;
    }

    public AddressModel getPrincipalAddress() {
        return principalAddress;
    }

    public void setPrincipalAddress(AddressModel idPrincipalAddress) {
        this.principalAddress = idPrincipalAddress;
    }
}
