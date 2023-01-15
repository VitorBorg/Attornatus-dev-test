package com.vitorBorg.Attornatus.controller;

import com.vitorBorg.Attornatus.DTO.PersonDTO;
import com.vitorBorg.Attornatus.model.AddressModel;
import com.vitorBorg.Attornatus.model.PersonModel;
import com.vitorBorg.Attornatus.service.AddressService;
import com.vitorBorg.Attornatus.service.PersonService;
import com.vitorBorg.Attornatus.utils.exception.Response.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {


    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/person")
    public ResponseEntity<Object> savePerson(@RequestBody PersonModel personModel){
        if(personService.existPersonByName(personModel.getNamePerson())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new StringResponse("Person name already exists."));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(personModel));
    }

    @GetMapping("/person")
    public ResponseEntity<Page<PersonModel>> getAllPerson(@PageableDefault(page=0, size = 10, sort="idPerson", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPerson(pageable));
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable(value="id") Long id){
        Optional<PersonDTO> personOptional = personService.getPersonById(id);

        if(!personOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Person not found."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(personOptional.get());
    }


    @DeleteMapping("person/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") Long id){
        Optional<PersonDTO> personOptional = personService.getPersonById(id);

        if(!personOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Person not found."));
        }

        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.OK).body(new StringResponse("Person was successfully deleted."));
    }

    @PutMapping("person/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value="id") long id, @RequestBody @Validated PersonModel personModel){
        Optional<PersonDTO> personOptional = personService.getPersonById(id);
        if(!personOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Person not found."));
        }

        Optional<AddressModel> addressOptional = addressService.getAddressById(personModel.getIdPrincipalAddress());
        if(!addressOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Address not found."));
        }

        if(addressOptional.get().getIdPerson() != id){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new StringResponse("Address belongs to another person."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(personService.updatePerson(personModel, id));
    }
}
