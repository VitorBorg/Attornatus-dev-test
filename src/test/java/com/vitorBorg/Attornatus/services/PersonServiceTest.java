package com.vitorBorg.Attornatus.services;

import com.vitorBorg.Attornatus.model.PersonModel;
import com.vitorBorg.Attornatus.repository.PersonRepository;
import com.vitorBorg.Attornatus.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    PersonModel personTest;

    @BeforeEach
    void setup_data_before_test() {
        PersonModel personTest = new PersonModel();
        personTest.setNamePerson("Ipsum");
        personTest.setBirthDatePerson("01-01-2000");


    }
    @Test
    public void should_be_able_to_create_a_new_person() {
        //Mockito.when(personRepository.save(personTest)).thenReturn(Optional.of(personTest));
        //PersonModel personReturn = personService.createPerson(personTest);
        Mockito.verify(personService).createPerson(personTest);
        //assertNotNull(personReturn.getIdPerson());
    }



}
