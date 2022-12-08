package org.disz.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.disz.demo.entity.Borrow;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class BookDto {
    // variables
    @JsonProperty
    private Long bookId;
    @JsonProperty
    @NotBlank(message = "Author must be set")
    private String author;
    @JsonProperty
    @NotBlank(message = "Title must be set")
    private String title;

    @JsonProperty
    @NotBlank(message = "Category must be set")
    private String category;

    @JsonProperty
    private List<BorrowDto> borrows = new ArrayList<>();

    // constructor

    public BookDto(){}
    public BookDto(Long bookID, String author, String category, String title) {
        this.bookId = bookID;
        this.author = author;
        this.category = category;
        this.title = title;
    }
    public BookDto(Long bookID, String author, String title, String category, List<BorrowDto> borrows) {
        this.bookId = bookID;
        this.author = author;
        this.title = title;
        this.category = category;
        this.borrows = borrows;
    }

    // getters
    public Long getBookId() {
        return bookId;
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
    public List<BorrowDto> getBorrows(){return borrows;}

    //setters
    public void setAuthor(String author) {this.author = author;}
    public void setTitle(String title) {this.title = title;}
    public void setCategory(String category) {
        this.category = category;
    }
}
