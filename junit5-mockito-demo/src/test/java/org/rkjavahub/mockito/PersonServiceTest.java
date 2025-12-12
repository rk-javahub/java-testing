package org.rkjavahub.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    void login() {
        //given
        Person person = new Person(7L, "Rohit Kumbhar", "kumbharrohit13@gmail.com", "rohit123");
        when(personRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.of(person));
        //when
        String token = personService.login("kumbharrohit13@gmail.com", "rohit123");
        //then
        assertThat(token).isNotNull();
        verify(personRepository, times(1)).findByEmailAndPassword(anyString(), anyString());
    }

    @Test
    void loginFailure() {
        //given
        when(personRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.empty());
        //when
        String token = personService.login("kumbharrohit13@gmail.com", "wrongpassword");
        //then
        assertThat(token).isNull();
        verify(personRepository, times(1)).findByEmailAndPassword(anyString(), anyString());
    }

    @Test
    void create() {
    }

    @Test
    void findByEmail() {
        //given
        Person person = new Person(7L, "Rohit Kumbhar", "kumbharrohit13@gmail.com", "rohit123");
        when(personRepository.findByEmail(anyString())).thenReturn(Optional.of(person));
        //when
        Optional<Person> optionalPerson = personService.findByEmail("kumbharrohit13");
        //then
        assertThat(optionalPerson).isPresent();
        assertThat(optionalPerson.get().getName()).isEqualTo("Rohit Kumbhar");
        verify(personRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void update() {
    }
}