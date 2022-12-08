package org.disz.demo.service;

import org.disz.demo.dto.BookDto;

import java.util.List;

public interface BookService {
    void addBook(BookDto book);
    void updateBook(BookDto bookDto, String author, String title, String category);

    void deleteBook(Long id);

    List<BookDto> findAllBooks();
    BookDto getById(Long id);
    List<BookDto> find(String query);
    List<BookDto> findBorrowedBooks(); // azok a könyvek, amik ki vannak adva
    List<BookDto> findBooksByCategory(String category);
}
