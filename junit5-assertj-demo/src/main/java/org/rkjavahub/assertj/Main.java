package org.rkjavahub.assertj;

import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PersonRepository repo = new PersonRepository();
        PersonService personService = new PersonService(repo);

        personService.create(new Person(null, "Rohit", "kumbharrohit13@gmail.com"));
        personService.create(new Person(null, "Sachin", "Sachin@gmail.com"));

        System.out.println("All Persons:");
        List<Person> people = personService.findAll();
        people.forEach(System.out::println);

        System.out.println("Find by Email ID:");
        Optional<Person> byEmail = personService.findByEmail("kumbharrohit13@gmail.com");
        System.out.println(byEmail);
    }
}