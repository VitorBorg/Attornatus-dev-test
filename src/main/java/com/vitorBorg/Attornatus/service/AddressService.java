package com.vitorBorg.Attornatus.service;

import com.vitorBorg.Attornatus.model.AddressModel;
import com.vitorBorg.Attornatus.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressModel createAddress(AddressModel addressModel){
        return addressRepository.save(addressModel);
    }
    public Page<AddressModel> getPeopleAddress(Long idPeople, Pageable pageable){
        return addressRepository.findByIdPerson(idPeople, pageable);
    }

    public Optional<AddressModel> getAddressById(Long id){
        Optional<AddressModel> address = addressRepository.findById(id);
        return address;
    }

    public void deleteAddressById(Long id){
        addressRepository.deleteById(id);
    }

    public AddressModel updateAddressById(AddressModel addressModel, Long id){
        Optional<AddressModel> dbDataAddressOptional = addressRepository.findById(id);

        if(!dbDataAddressOptional.isPresent()){
            return null;
        }

        dbDataAddressOptional.get().setLogradouroAddress(addressModel.getLogradouroAddress());
        dbDataAddressOptional.get().setCepAddress(addressModel.getCepAddress());
        dbDataAddressOptional.get().setNumberAddress(addressModel.getNumberAddress());
        dbDataAddressOptional.get().setCityAddress(addressModel.getCityAddress());

        return addressRepository.save(dbDataAddressOptional.get());
    }
}
