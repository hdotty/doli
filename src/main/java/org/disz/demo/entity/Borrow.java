package org.disz.demo.entity;

import org.disz.demo.dto.BookDto;
import org.disz.demo.dto.PersonDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Borrow {
    // variables
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @ManyToOne
    private Person person;

    @NonNull
    @ManyToOne
    private Book book;

    @NonNull
    LocalDate startTime;

    @Nullable
    LocalDate endTime;

    // constructors
    public Borrow(){}

    public Borrow(Person person, Book book, LocalDate startTime, LocalDate endTime) {
        this.person = person;
        this.book = book;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Borrow(Long id, Person person, Book book, LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.person = person;
        this.book = book;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getters
    public Long getId(){return id;}
    public Person getPerson() {
        return person;
    }
    public Book getBook() {
        return book;
    }
    public LocalDate getStartTime() {
        return startTime;
    }
    public LocalDate getEndTime() {
        return endTime;
    }

    // setters
    public void setId(Long id){this.id = id;}
    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }


}
