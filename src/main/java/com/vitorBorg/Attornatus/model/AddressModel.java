package com.vitorBorg.Attornatus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddressModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idAddress;
    private Long idPerson;
    private String logradouroAddress;
    private String cepAddress;
    private Integer numberAddress;
    private String cityAddress;

    public AddressModel(Long idAddress, String logradouroAddress, String cepAddress, Integer numberAddress, String cityAddress, long idPerson) {
        this.idAddress = idAddress;
        this.logradouroAddress = logradouroAddress;
        this.cepAddress = cepAddress;
        this.numberAddress = numberAddress;
        this.cityAddress = cityAddress;
        this.idPerson = idPerson;
    }

    public AddressModel(){}

    public Long getIdAddress() {
        return idAddress;
    }
    public Long getIdPerson() {
        return idPerson;
    }

    public String getLogradouroAddress() {
        return logradouroAddress;
    }

    public String getCepAddress() {
        return cepAddress;
    }

    public Integer getNumberAddress() {
        return numberAddress;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public void setLogradouroAddress(String logradouroAddress) {
        this.logradouroAddress = logradouroAddress;
    }

    public void setCepAddress(String cepAddress) {
        this.cepAddress = cepAddress;
    }

    public void setNumberAddress(Integer numberAddress) {
        this.numberAddress = numberAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }



}
