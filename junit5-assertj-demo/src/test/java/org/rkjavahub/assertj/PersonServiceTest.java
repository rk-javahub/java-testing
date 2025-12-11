package org.rkjavahub.assertj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PersonServiceTest {
    PersonService personService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
        PersonRepository personRepository = new PersonRepository();
        personService = new PersonService(personRepository);
        Person person = new Person(7L, "Rohit", "kumbharrohit13@gmail.com");
        Person createdPerson = personService.create(person);
        assertThat(createdPerson.getId()).isNotNull();
        assertThat(createdPerson.getName()).isEqualTo("Rohit");
        assertThat(createdPerson.getEmail()).isEqualTo("kumbharrohit13@gmail.com");
    }

    @Test
    void findById() {
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