package com.vitorBorg.Attornatus.service;

import com.vitorBorg.Attornatus.DTO.PersonDTO;
import com.vitorBorg.Attornatus.model.AddressModel;
import com.vitorBorg.Attornatus.utils.exception.ObjectNotFoundException;
import com.vitorBorg.Attornatus.model.PersonModel;
import com.vitorBorg.Attornatus.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressService addressService;


    PersonModel personTest;
    Optional<PersonModel> personTestOptional;
    PersonDTO personDTOTest;
    Optional<PersonDTO> personDTOTestOptional;
    Optional<AddressModel> addressTestOptional;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        starting_user();
    }

    private void starting_user(){
        personTest = new PersonModel(Long.valueOf(1), "Ipsum", LocalDate.parse("2000-01-01"), Long.valueOf(1));
        personTestOptional = Optional.of(personTest);
        addressTestOptional = Optional.of(new AddressModel(Long.valueOf(1), "Logradouro", "000", "0", "City", Long.valueOf(1)));
        personDTOTest = new PersonDTO(Long.valueOf(1), "Ipsum", LocalDate.parse("2000-01-01"), addressTestOptional.get());
        personDTOTestOptional = Optional.of(personDTOTest);
    }

    @Test
    void should_be_able_to_create_a_new_person() {
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(personTest);

        PersonModel response = personService.createPerson(personTest);

        assertNotNull(response);
        assertEquals(PersonModel.class, response.getClass());
        assertEquals(personTest.getIdPerson(), response.getIdPerson());
        assertEquals(personTest.getNamePerson(), response.getNamePerson());
        assertEquals(personTest.getBirthDatePerson(), response.getBirthDatePerson());
        assertEquals(personTest.getIdPrincipalAddress(), response.getIdPrincipalAddress());
    }

    @Test
    void should_be_able_to_find_a_person_by_id() {
        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(personTestOptional);
        Mockito.when(addressService.getAddressById(Mockito.anyLong())).thenReturn(addressTestOptional);
        Optional<PersonDTO> response = personService.getPersonById(Long.valueOf(1));

        assertEquals(response.isPresent(), true);
        //assertEquals(response.getClass(), O);
        assertEquals(PersonDTO.class, response.get().getClass());
        assertEquals(response.get().getIdPerson(), personTest.getIdPerson());
        assertEquals(response.get().getNamePerson(), personTest.getNamePerson());
        assertEquals(response.get().getBirthDatePerson(), personTest.getBirthDatePerson());
        assertEquals(response.get().getPrincipalAddress().getIdAddress(), personTest.getIdPrincipalAddress());
    }

    @Test
    void should_be_able_to_find_a_person_by_name() {
        Mockito.when(personRepository.existsByNamePerson(Mockito.any())).thenReturn(true);
        Boolean response = personService.existPersonByName(personTest.getNamePerson());

        assertEquals(true, response);
    }

    @Test
    void should_be_able_to_update_a_person() {
        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(personTestOptional);
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(personTest);

        PersonModel response = personService.updatePerson(personTest, Long.valueOf(1));

        assertNotNull(response);
        assertEquals(PersonModel.class, response.getClass());
        assertEquals(personTest.getIdPerson(), response.getIdPerson());
        assertEquals(personTest.getNamePerson(), response.getNamePerson());
        assertEquals(personTest.getBirthDatePerson(), response.getBirthDatePerson());
        assertEquals(personTest.getIdPrincipalAddress(), response.getIdPrincipalAddress());
    }

    @Test
    void should_be_able_to_delete_a_person() {
        doNothing().when(personRepository).deleteById(Mockito.anyLong());
        personService.deletePerson(Long.valueOf(1));

        verify(personRepository, times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void should_not_be_able_to_delete_a_person() {
        when(personRepository.findById(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException("Person not found."));
    }
}