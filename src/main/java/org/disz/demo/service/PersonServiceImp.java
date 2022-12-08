package org.disz.demo.service;

import org.disz.demo.dto.BorrowDto;
import org.disz.demo.dto.PersonDto;
import org.disz.demo.entity.Person;
import org.disz.demo.repository.BorrowRepository;
import org.disz.demo.repository.PersonRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp implements PersonService{
    public final PersonRepository personRepository;
    public final BorrowRepository borrowRepository;
    public final BorrowServiceImp borrowServiceImp;
    private final PasswordEncoder passwordEncoder;

    public PersonServiceImp(PersonRepository personRepository, BorrowRepository borrowRepository, BorrowServiceImp borrowServiceImp,
                            PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.borrowRepository = borrowRepository;
        this.borrowServiceImp = borrowServiceImp;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void addPerson(PersonDto personDto) {
        if (personRepository.existsByEmail(personDto.getEmail())) {
            throw new IllegalArgumentException("A person with that email address already exists");
        }
        personRepository.save(toEntity(personDto));
    }
    @Override
    public void updatePerson(PersonDto personDto, String firstName, String lastName, String email){
        Person person = toEntity(personDto);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        personRepository.save(person);
    }
    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
    @Override
    public List<PersonDto> findAllPerson() {
        return personRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    @Override
    public PersonDto getById(Long id) {
        return personRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    @Override
    public PersonDto getByEmail(String email) {
        return toDto(personRepository.getByEmail(email));
    }

    @Override
    public List<PersonDto> findPersonByFirstNameOrLastNameOrEmail(String query) {
        return personRepository.findPersonByFirstNameOrLastNameOrEmail(query, query, query).stream().map(this::toDto).collect(Collectors.toList());
    }
    @Override
    public void changePsw(PersonDto personDto, String oldPsw, String newPsw){
        Person person = toEntity(personDto);
        if(Objects.equals(person.getPassword(), oldPsw)){
            person.setPassword(newPsw);
            personRepository.save(person);
        }
    }
    @Override
    public List<BorrowDto> personsBooks(PersonDto personDto) {return personDto.getBorrows();}

    @Override
    public PersonDto getLoggedInPerson() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        final Person person = personRepository.getByEmail(authentication.getName());
        if (person == null) {
            return null;
        }

        return toDto(person);
    }


    private Person toEntity(PersonDto personDto) {
        final String encodedPassword = passwordEncoder.encode(personDto.getPassword());
        return new Person(personDto.getPersonId(), personDto.getFirstName(), personDto.getLastName(), personDto.getEmail(), encodedPassword);
    }

    private <S extends PersonDto> PersonDto toDto(Person person) {
        return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(),person.getEmail(),person.isAdmin(),person.getPassword(), borrowServiceImp.toDtos(person.getBorrows()));
    }

}
