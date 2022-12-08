package org.disz.demo.service;

import org.disz.demo.entity.Book;
import org.disz.demo.dto.BookDto;
import org.disz.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {
    public final BookRepository bookRepository;
    public final BorrowServiceImp borrowServiceImp;

    public BookServiceImp(BookRepository bookRepository, BorrowServiceImp borrowServiceImp){
        this.bookRepository = bookRepository;
        this.borrowServiceImp = borrowServiceImp;
    }
    @Override
    public void addBook(BookDto book) {bookRepository.save(toEntity(book));}
    @Override
    public void updateBook(BookDto bookDto, String author, String title, String category){
        Book book = toEntity(bookDto);
        book.setAuthor(author);
        book.setTitle(title);
        book.setCategory(category);
        bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public List<BookDto> findAllBooks() {return toDtos(bookRepository.findAll());}
    @Override
    public BookDto getById(Long id) {return bookRepository.findById(id).map(this::toDto).orElse(null);}
    @Override
    public List<BookDto> find(String query) {
        return toDtos(bookRepository.findAllByAuthorContainsOrTitleContains(query, query));
    }

    @Override
    public List<BookDto> findBorrowedBooks() {
        return bookRepository.findAllBorrowed().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBooksByCategory(String category) {
        return bookRepository.findBooksByCategory(category).stream().map(this::toDto).collect(Collectors.toList());
    }

    private Book toEntity(BookDto book) {
        return new Book(book.getBookId(), book.getAuthor(), book.getTitle(), book.getCategory());
    }

    public <S extends BookDto> BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getCategory(), borrowServiceImp.toDtos(book.getBorrows()));
    }

    public List<Book> valami() {
        return bookRepository.findAllNotBorrowed();
    }


    public List<BookDto> toDtos(List<Book> books) {
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }
}
