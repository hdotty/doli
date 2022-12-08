package org.disz.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class PersonDto {
    @JsonProperty
    private Long personId;
    @JsonProperty
    @NotBlank(message = "First name must be set")
    private String firstName;
    @JsonProperty
    @NotBlank(message = "Last name must be set")
    private String lastName;
    @JsonProperty
    @NotBlank(message = "Email must be set")
    @Column(unique = true)
    private String email;
    @JsonProperty
    private boolean admin = false;
    @JsonProperty
    @NotBlank(message = "Password must be set")
    private String password;
    @JsonProperty
    private List<BorrowDto> borrows;

    private boolean loggedIn = false;

    public PersonDto(){}
    public PersonDto(Long personId, String firstName, String lastName, String email, boolean admin, String password, List<BorrowDto> borrows) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
        this.password = password;
        this.borrows = borrows;
    }

    public PersonDto(Long personId, String firstName, String lastName, String email, String password) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public PersonDto(Long id, String firstName, String lastName, String email, String password, boolean admin) {
        this.personId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public Long getPersonId() {return personId;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getEmail() {return email;}

    public boolean isAdmin() {return admin;}

    public String getPassword() {return password;}

    public List<BorrowDto> getBorrows() {return borrows;}

    public boolean isLoggedIn(){return loggedIn;}

    public void setLoggedIn(boolean loggedIn){this.loggedIn = loggedIn;}
}
