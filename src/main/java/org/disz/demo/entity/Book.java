package org.disz.demo.entity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Book {
    // variables
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String author;

    @NonNull
    private String title;

    @Nullable
    private String category;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows = new ArrayList<>();

    // constructor
    public Book(){}

    public Book(Long id, String author, String title, String category) {
        this.id = Optional.ofNullable(id).orElse(0L);
        this.author = author;
        this.title = title;
        this.category = category;
    }
    public Book(Long id, String author, String title, String category, List<Borrow> borrows) {
        this.id = Optional.ofNullable(id).orElse(0L);
        this.author = author;
        this.title = title;
        this.category = category;
        this.borrows = borrows;
    }
    // getters
    public Long getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public String getCategory() {
        return category;
    }
    public List<Borrow> getBorrows() {
        return borrows;
    }

    // setter
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setBorrows(List borrows){this.borrows = borrows;}
}
