package challenge;

import challenge.entities.Person;
import challenge.services.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;



import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MyApplicationTest {

    @Autowired
    private PersonService personService;

    @Test
    public void addingPerson() {
        Person person = new Person("Matti","Keskiortentie 75", "0413878124", Instant.now());
        personService.addPerson(person);

        assertEquals(person, personService.findPerson("Matti").get(0));
        assertEquals(1, personService.getAllPersons().size());
    }

    @Test
    public void deletingPerson() {
        Person person = new Person("Matti","Keskiortentie 75", "0413878124", Instant.now());
        personService.addPerson(person);
        assertEquals(1, personService.getAllPersons().size());
        personService.deletePersonById(1L);
        assertEquals(0, personService.getAllPersons().size());

    }

    @Test
    public void updatingPerson() {
        Person person = new Person("Matti","Keskiortentie 75", "0413878124", Instant.now());
        Person person2 = new Person("Jesse","Testikatu", "0413878124", Instant.now());

        personService.addPerson(person);
        personService.addPerson(person2);

        assertEquals(person, personService.findPerson("Matti").get(0));
        person.setName("Jaakko");
        personService.updatePersonByID(1L, person);

        assertEquals(person, personService.findPerson("Jaakko").get(0));

    }

    @Test
    public void findingPersonByName() {
        String names[] = {"Jaakko", "Matti", "Ismo", "Pentti"};
        String addresses[] = {"Testi", "Testikatu", "Testikuja", "Testitie"};
        Person first = new Person(names[0], addresses[0], "112", Instant.now());
        personService.addPerson(first);
        for (int i = 1; i < names.length; i++) {
            personService.addPerson(new Person(names[i], addresses[i], "112", Instant.now()));
        }

        assertEquals(first, personService.findPerson(names[0]).get(0));

    }

}