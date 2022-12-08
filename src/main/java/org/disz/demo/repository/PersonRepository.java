package org.disz.demo.repository;
import org.disz.demo.dto.PersonDto;
import org.disz.demo.entity.Book;
import org.disz.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository <Person, Long> {
    List<Person> findPersonByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email);

    boolean existsByEmail(String email);

    Person getByEmail(String email);
}
