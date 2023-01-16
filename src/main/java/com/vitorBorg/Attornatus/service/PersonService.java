package com.vitorBorg.Attornatus.service;

import com.vitorBorg.Attornatus.DTO.PersonDTO;
import com.vitorBorg.Attornatus.model.AddressModel;
import com.vitorBorg.Attornatus.model.PersonModel;
import com.vitorBorg.Attornatus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressService addressService;

    public PersonModel createPerson(PersonModel personModel){
        return personRepository.save(personModel);
    }

    public Optional<PersonDTO> getPersonById(Long id){
        Optional<PersonModel> personModel = personRepository.findById(id);

        if(!personModel.isPresent()){
            return null;
        }

        Optional<AddressModel> addressModel = addressService.getAddressById(id);
        Optional<PersonDTO> person = Optional.of(new PersonDTO(
                personModel.get().getIdPerson(),
                personModel.get().getNamePerson(),
                personModel.get().getBirthDatePerson(),
                null
        ));

        if(addressModel.isPresent()) {
            person.get().setPrincipalAddress(addressModel.get());
        }

        return person;
    }
    public Page<PersonModel> getAllPerson(Pageable pageable){
        return personRepository.findAll(pageable);
    }

    public Boolean existPersonByName(String name){
        return personRepository.existsByNamePerson(name);
    }


    public PersonModel updatePerson(PersonModel personModel, Long id){
        Optional<PersonModel> dbDataPersonOptional = personRepository.findById(id);


        if(!dbDataPersonOptional.isPresent()){
            return null;
            //return dbDataPersonOptional.orElseThrow(() -> new ObjectNotFoundException("Person not found"));
        }

        dbDataPersonOptional.get().setNamePerson(personModel.getNamePerson());
        dbDataPersonOptional.get().setBirthDatePerson(personModel.getBirthDatePerson());

        if(personModel.getIdPrincipalAddress() != null){
            dbDataPersonOptional.get().setIdPrincipalAddress(personModel.getIdPrincipalAddress());
        }

        return personRepository.save(dbDataPersonOptional.get());
    }

    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }


}
