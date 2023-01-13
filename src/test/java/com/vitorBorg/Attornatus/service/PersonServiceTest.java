package com.vitorBorg.Attornatus.service;

import com.vitorBorg.Attornatus.model.PersonModel;
import com.vitorBorg.Attornatus.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;


    PersonModel personTest;
    Optional<PersonModel> personTestOptional;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        starting_user();
    }

    private void starting_user(){
        personTest = new PersonModel();
        personTest.setNamePerson("Ipsum");
        personTest.setBirthDatePerson("01-01-2000");

        personTestOptional = Optional.of(personTest);
    }

    @Test
    void should_be_able_to_create_a_new_person() {
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(personTest);

        PersonModel response = personService.createPerson(personTest);

        assertNotNull(response);
        assertEquals(PersonModel.class, response.getClass());
        assertEquals(personTest.getNamePerson(), response.getNamePerson());
        assertEquals(personTest.getBirthDatePerson(), response.getBirthDatePerson());
    }

    @Test
    void should_not_be_able_to_create_a_new_person() {
    }

    @Test
    void should_not_be_able_to_set_other_person_address_as_principal() {
    }
    @Test
    void should_be_able_to_find_a_user_by_id() {
        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(personTestOptional);
        Optional<PersonModel> response = personService.getPersonById(Long.valueOf(1));
        System.out.println(response.get().getIdPerson());

        Assertions.assertEquals(personTest.getClass(), response.getClass());
    }

    @Test
    void getAllPerson() {
    }

    @Test
    void existPersonByName() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }
}