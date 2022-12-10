package org.disz.demo.entity;

import javax.persistence.*;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Person {
    // variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @Column(unique = true)
    private String email;
    private boolean admin;
    @NonNull
    private String password;

    @OneToMany(mappedBy = "person")
    private List<Borrow> borrows = new ArrayList<>();

    // constructor
    public Person(){}
    public Person(Long personId, String firstName, String lastName, String email, String password){
        this.id = Optional.ofNullable(personId).orElse(0L);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = false;
    }

    public Person(String firstName, String lastName, String email, String password, boolean admin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public Person(Long personId, String firstName, String lastName, String email, String password, boolean admin) {
        this.id = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    //getters
    public long getId(){return this.id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {
        return email;
    }
    public boolean isAdmin() {
        return admin;
    }
    public String getPassword() {return password;}
    public List<Borrow> getBorrows() {
        return borrows;
    }

    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public void setBorrows(List borrows){this.borrows = borrows;}
}

