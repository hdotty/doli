package org.disz.demo.service;

import org.disz.demo.dto.BookDto;
import org.disz.demo.dto.BorrowDto;
import org.disz.demo.dto.PersonDto;
import org.disz.demo.entity.Book;
import org.disz.demo.entity.Borrow;
import org.disz.demo.entity.Person;
import org.disz.demo.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImp implements BorrowService{
    private final BorrowRepository borrowRepository;

    public BorrowServiceImp(final BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;}

    @Override
    public void addBorrow(BorrowDto borrowDto) {
        //if (borrowDto.getEndTime() )
        borrowRepository.save(toEntity(borrowDto));
    }

    @Override
    public void returnBook(BorrowDto borrowDto){
        if (borrowDto.getEndTime().isAfter(borrowDto.getStartTime())) {
            borrowRepository.save(toEntity(borrowDto));
        }
    }

    @Override
    public void deleteAllByPersonId(long personId){
        borrowRepository.deleteAllByPersonId(personId);
    }
    @Override
    public void deleteAllByBookId(long bookId){
        borrowRepository.deleteAllByBookId(bookId);
    }

    @Override
    public List<BorrowDto> findAll() { return toDtos(borrowRepository.findAll());}

    @Override
    public List<BorrowDto> findBorrowByBookId(Long bookId) {  // az adott könyvet kik kölcsönözték ki
        return toDtos(borrowRepository.findAllByBookId(bookId));
    }

    @Override
    public List<BorrowDto> findBorrowByPersonId(Long personId) {
        return toDtos(borrowRepository.findAllByPersonId(personId)); }

    @Override
    public List<BorrowDto> findBorrowByStartTimeIsNotNullAndEndTimeIsNull() {
        return toDtos(borrowRepository.findAllByEndTimeIsNull());}
    @Override
    public long nowBorrowedBooks() {
        return borrowRepository.countByEndTimeIsNull();}
    @Override
    public List<BorrowDto> findBorrowByStartTimeAndEndTimeIsNotNull() {
        return toDtos(borrowRepository.findAllByEndTimeIsNotNull()) ;
    }
    @Override
    public long returnedBorrowes() {
        return borrowRepository.countByEndTimeIsNotNull();}
    @Override
    public List<BorrowDto> findBorrowByStartTimeAndEndTimeIsNull() {
        return toDtos(borrowRepository.findAllByEndTimeIsNull());}
    @Override
    public long notBorrowedYet() {
        return borrowRepository.countByEndTimeIsNull();
    }

    @Override
    public List<BorrowDto> findByAuthorOrTitleContaining(String search) {
        return toDtos(borrowRepository.findByBookAuthorContainingOrBookTitleContaining(search, search));
    }

    private Person toPersonEntity(PersonDto personDto) {
        return new Person(personDto.getPersonId(), personDto.getFirstName(), personDto.getLastName(), personDto.getEmail(), personDto.getPassword(), personDto.isAdmin());
    }
    private PersonDto toPersonDto (Person person){
        return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(), person.getPassword(), person.isAdmin());
    }


    private Book toBookEntity(BookDto book) {
        return new Book(book.getBookId(), book.getAuthor(), book.getTitle(), book.getCategory());
    }

    public BookDto toBookDto(Book book) {
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getCategory());
    }


    public Borrow toEntity(BorrowDto borrow){
        return new Borrow(
                borrow.getId(),
                toPersonEntity(borrow.getPerson()),
                toBookEntity(borrow.getBook()),
                borrow.getStartTime(),
                borrow.getEndTime());}
    public BorrowDto toDto(Borrow borrow){
        return new BorrowDto(
                borrow.getId(),
                toPersonDto(borrow.getPerson()),
                toBookDto(borrow.getBook()),
                borrow.getStartTime(),
                borrow.getEndTime());}
    public List<BorrowDto> toDtos(List<Borrow> borrows){
        return borrows.stream().map(this::toDto).collect(Collectors.toList());
    }
}

