package org.rkjavahub.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    PersonService personService;
    public static Person person1;

    @BeforeEach
    void setUp() {
        PersonRepository personRepository = new PersonRepository();
        personService = new PersonService(personRepository);
        person1 = new Person(17L,"Sachin","sachin12@gmail.com");
        personService.create(person1);
        System.out.println(person1.getName());
    }

    @Test
    void create() {
        Person person = new Person(7L,"Rohit","kumbharrohit13@gmail.com");
        Person createdPerson = personService.create(person);
        assertNotNull(createdPerson.getId());
        assertEquals("Rohit", createdPerson.getName());
        assertEquals("kumbharrohit13@gmail.com", createdPerson.getEmail());
    }

    @Test
    void findById() {
       /* person1 = new Person(17L,"Sachin","sachin12@gmail.com");
        Person personOptional = personService.findById(17L).get();
        //assertTrue(personOptional.isPresent());
        assertEquals(17L, personOptional.getId());
        assertEquals("Sachin", personOptional.getName());
        assertEquals("sachin12@gmail.com", personOptional.getEmail());*/
    }

    @Test
    void findAll() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}