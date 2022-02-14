package challenge.services;

import challenge.entities.Person;
import challenge.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public ResponseEntity<Person> updatePersonByID(Long id, Person param) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            Person person = p.get();

            String name = param.getName() == null ? person.getName() : param.getName();
            person.setName(name);

            String address = param.getAddress() == null ? person.getAddress() : param.getAddress();
            person.setAddress(address);

            String number = param.getPhoneNumber() == null ? person.getPhoneNumber() : param.getPhoneNumber();
            person.setPhoneNumber(number);

            return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find person with given id");
    }

    public Person deletePersonById(Long id) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            Person person = p.get();
            personRepository.delete(person);
            return person;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find person with given id");
    }


    public List<Person> findPerson(String name) {
        Optional<List<Person>> persons = personRepository.findByNameIgnoreCase(name);
        if (persons.isPresent()) {
            return persons.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find person(s) with given name");
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person addPerson (Person person) {
        return personRepository.save(person);
    }



}
