package org.disz.demo.service;

import org.disz.demo.dto.BorrowDto;
import org.disz.demo.dto.PersonDto;

import java.util.List;

public interface PersonService {

    void addPerson(PersonDto personDto);
    void updatePerson(PersonDto personDto, String firstName, String lastName, String email);
    void deletePerson(Long id);

    List<PersonDto> findAllPerson();
    PersonDto getById(Long id);
    boolean existsByEmail(String email);
    PersonDto getByEmail(String email);
    List<PersonDto> findPersonByFirstNameOrLastNameOrEmail(String query);
    void changePsw(PersonDto personDto, String oldPsw, String newPsw);
    List<BorrowDto> personsBooks(PersonDto personDto);

    PersonDto getLoggedInPerson();
}
