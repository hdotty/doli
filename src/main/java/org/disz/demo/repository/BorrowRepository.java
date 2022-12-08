package org.disz.demo.repository;

import org.disz.demo.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findAllByBookId(Long bookId);
    List<Borrow> findAllByPersonId(Long personId);
    void deleteAllByPersonId(long id);
    void deleteAllByBookId(long bookId);

    //hány könyv van épp kölcsönözve
    List<Borrow> findAllByEndTimeIsNull();
    List<Borrow> findAllByEndTimeIsNotNull();
    long countByEndTimeIsNull();
    long countByEndTimeIsNotNull();

    List<Borrow> findByBookAuthorContainingOrBookTitleContaining(String author, String title);


}
