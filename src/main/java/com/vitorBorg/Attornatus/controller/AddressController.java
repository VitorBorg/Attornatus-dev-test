package com.vitorBorg.Attornatus.controller;

import com.vitorBorg.Attornatus.DTO.PersonDTO;
import com.vitorBorg.Attornatus.model.AddressModel;
import com.vitorBorg.Attornatus.service.AddressService;
import com.vitorBorg.Attornatus.service.PersonService;
import com.vitorBorg.Attornatus.utils.response.StringResponse;
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
public class AddressController {


    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonService personService;

    @PostMapping("/address")
    public ResponseEntity<Object> saveAddress(@RequestBody AddressModel addressModel){
        Optional<PersonDTO> personOptional = personService.getPersonById(addressModel.getIdPerson());

        if(personOptional == null || !personOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Person not found."));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(addressModel));
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Page<AddressModel>> getPeopleAddress(@PathVariable(value="id") Long id, @PageableDefault(page=0, size = 10, sort="idAddress", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getPeopleAddress(id, pageable));
    }

    @GetMapping("/address/principal/{id}")
    public ResponseEntity<Object> getPrincipalAddress(@PathVariable(value="id") Long id){
        Optional<PersonDTO> personOptional = personService.getPersonById(id);

        if(!personOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Person not found."));
        }

        if(personOptional.get().getPrincipalAddress() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("This person there is no principal address."));
        }

        Optional<AddressModel> addressOptional = addressService.getAddressById((personOptional.get().getPrincipalAddress().getIdAddress()));

        if(!addressOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Address not found."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(addressOptional.get());
    }


    @DeleteMapping("address/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") Long id){
        Optional<AddressModel> addressOptional = addressService.getAddressById(id);

        if(!addressOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Address not found."));
        }

        addressService.deleteAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new StringResponse("Address was successfully deleted."));
    }

    @PutMapping("address/{id}")
    public ResponseEntity<Object> updateAddressById(@PathVariable(value="id") long id, @RequestBody @Validated AddressModel addressModel){
        Optional<AddressModel> addressOptional = addressService.getAddressById(id);

        if(!addressOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponse("Address not found."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddressById(addressModel, id));
    }
}
