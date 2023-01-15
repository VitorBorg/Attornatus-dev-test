package com.vitorBorg.Attornatus.service;

import com.vitorBorg.Attornatus.model.PersonModel;
import com.vitorBorg.Attornatus.repository.PersonRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonModel createPerson(PersonModel personModel){
        return personRepository.save(personModel);
    }

    public Optional<PersonModel> getPersonById(Long id){
        Optional<PersonModel> person = personRepository.findById(id);
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
