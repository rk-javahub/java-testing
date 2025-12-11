package org.rkjavahub.assertj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PersonServiceTest {
    PersonService personService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
        PersonRepository personRepository = new PersonRepository();
        personService = new PersonService(personRepository);
        Person person = new Person(null, "Rohit", "kumbharrohit13@gmail.com");
        Person createdPerson = personService.create(person);
        assertThat(createdPerson.getId()).isNotNull();
        assertThat(createdPerson.getName()).isEqualTo("Rohit");
        assertThat(createdPerson.getEmail()).isEqualTo("kumbharrohit13@gmail.com").endsWith("@gmail.com");
        assertThat(createdPerson.getName()).contains("R");
        assertThat(createdPerson.getId()).isGreaterThan(0L);
        assertThat(createdPerson.getName()).containsIgnoringCase("roh");
    }

    @Test
    void create_shouldThrowWhenNameIsEmpty() {
        PersonRepository personRepository = new PersonRepository();
        PersonService personService = new PersonService(personRepository);
        Person person = new Person(null, null, "someone@example.com");

        assertThatThrownBy(() -> personService.create(person))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Name is required");
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