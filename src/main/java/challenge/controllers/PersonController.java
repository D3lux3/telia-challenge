package challenge.controllers;

import challenge.entities.Person;
import challenge.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<Person> getPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable String name) {
        return new ResponseEntity<>(personService.findPerson(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson (@PathVariable Long id, @Valid @RequestBody Person person) {
        return personService.updatePersonByID(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson (@PathVariable Long id) {
        return new ResponseEntity<>(personService.deletePersonById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> addPerson (@Valid @RequestBody Person person) {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.OK);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleNameValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put("error", error.getDefaultMessage());
        }
        errors.put("timestamp", LocalDateTime.now().toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, String> handleNotFoundException(ResponseStatusException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getReason());
        errors.put("timestamp", LocalDateTime.now().toString());
        return errors;
    }


}
