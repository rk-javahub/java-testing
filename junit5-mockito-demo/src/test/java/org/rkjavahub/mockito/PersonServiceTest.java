package org.rkjavahub.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
        //given
        when(personRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(personRepository.create(any(Person.class))).thenAnswer(i -> i.getArgument(0));
        //when
        Person createdPerson = personService.create("Rohit Kumbhar", "kumbharrohit13@gmail.com", "rohit123");
        //then
        assertThat(createdPerson).isNotNull();
        assertThat(createdPerson.getName()).isEqualTo("Rohit Kumbhar");

        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository, times(1)).create(captor.capture());
        Person capturedPerson = captor.getValue();
        assertThat(capturedPerson.getEmail()).isEqualTo("kumbharrohit13@gmail.com");
        assertThat(capturedPerson.getName()).isEqualTo("Rohit Kumbhar");
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
        Person person = new Person(1L, "Siva", "siva@gmail.com", "siva123");

        doNothing().when(personRepository).update(any(Person.class));
        //doThrow(new RuntimeException("Invalid email")).when(repo).update(any(Person.class));

        personService.update(person);

        verify(personRepository).update(any(Person.class));
        //verify(repo, times(1)).update(any(Person.class));
        //verify(repo, atMostOnce()).update(any(Person.class));
        //verify(repo, atLeastOnce()).update(any(Person.class));
    }
}