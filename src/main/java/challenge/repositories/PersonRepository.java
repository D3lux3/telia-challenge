package challenge.repositories;


import challenge.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<List<Person>> findByNameIgnoreCase(String name);
    
}
