package org.disz.demo.repository;

import org.disz.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthorContainsOrTitleContains(String query, String query1);

    @Query("SELECT book FROM Book book LEFT JOIN book.borrows borrow ON borrow.endTime IS NULL WHERE borrow.id IS NULL")
    List<Book> findAllNotBorrowed();

    @Query("SELECT book FROM Book book LEFT JOIN book.borrows borrow ON borrow.endTime IS NULL WHERE borrow.id IS NOT NULL")
    List<Book> findAllBorrowed();


    @Query("SELECT book FROM Book book WHERE category=?1")
    List<Book> findBooksByCategory(String category);
}
