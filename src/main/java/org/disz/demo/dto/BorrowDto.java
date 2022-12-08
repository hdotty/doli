package org.disz.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class BorrowDto {
    @JsonProperty
    private long id;

    @JsonProperty
    @NonNull
    private PersonDto person;
    @JsonProperty
    @NonNull
    private BookDto book;
    @JsonProperty
    @NonNull
    @NotBlank(message = "Start Date must be set")
    LocalDate startTime;
    @JsonProperty
    @Nullable
    LocalDate endTime;

    public BorrowDto(){}
    public BorrowDto(PersonDto person, BookDto book, LocalDate startTime, LocalDate endTime){
        this.person = person;
        this.book = book;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public BorrowDto(Long id, PersonDto person, BookDto book, LocalDate startTime, LocalDate endTime){
        this.id = id;
        this.person = person;
        this.book = book;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    //getters
    public Long getId(){return id;}
    public PersonDto getPerson() {
        return person;}
    public BookDto getBook(){
        return book;}
    public LocalDate getStartTime(){
        return startTime;}
    public LocalDate getEndTime(){
        return endTime;}

    //setters
    public void setId(Long id){this.id = id;}
    public void setPerson(@NonNull PersonDto personDto) {
        this.person = personDto;}
    public void setBook(@NonNull BookDto bookDto) {
        this.book = bookDto;}
    public void setStartTime(@NonNull LocalDate startTime) {
        this.startTime = startTime;}
    public void setEndTime(@Nullable LocalDate endTime) {
        this.endTime = endTime;}
}
