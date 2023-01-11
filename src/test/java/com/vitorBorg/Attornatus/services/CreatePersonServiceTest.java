package com.vitorBorg.Attornatus.services;

import com.vitorBorg.Attornatus.model.PersonModel;
import com.vitorBorg.Attornatus.service.PersonService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreatePersonServiceTest {

    @Test
    public void should_be_able_to_create_a_new_person() {
        PersonModel personTest = new PersonModel();
        personTest.setNamePerson("Ipsum");
        personTest.setBirthDatePerson("01-01-2000");

        PersonService personService = new PersonService();
        PersonModel personReturn = personService.createPerson(personTest);

        assertNotNull(personReturn.getIdPerson());
    }
}
