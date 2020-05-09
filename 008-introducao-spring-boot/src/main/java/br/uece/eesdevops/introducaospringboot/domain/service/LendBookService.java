package br.uece.eesdevops.introducaospringboot.domain.service;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLendingStatus;
import br.uece.eesdevops.introducaospringboot.domain.entity.Student;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookAlreadyLentException;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookNotFoundException;
import br.uece.eesdevops.introducaospringboot.domain.exception.InvalidBookLendingException;
import br.uece.eesdevops.introducaospringboot.domain.exception.StudentNotFoundException;
import br.uece.eesdevops.introducaospringboot.repository.BookLendingRepository;
import br.uece.eesdevops.introducaospringboot.repository.BookRepository;
import br.uece.eesdevops.introducaospringboot.repository.StudentRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LendBookService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final BookLendingRepository bookLendingRepository;

    public LendBookService(
            BookRepository bookRepository,
            StudentRepository studentRepository,
            BookLendingRepository bookLendingRepository
    ) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.bookLendingRepository = bookLendingRepository;
    }

    @Transactional
    public BookLending execute(BookLending bookLending) {
        requireBookAndStudent(bookLending);

        Book book = getBookOrThrow(bookLending.getBook().getId());
        Student student = getStudentOrThrow(bookLending.getStudent().getId());

        checkIfBookIsAlreadyLent(bookLending.getBook().getId());

        bookLending.setBook(book);
        bookLending.setStudent(student);
        bookLending.setStatus(BookLendingStatus.LENT);

        return bookLendingRepository.save(bookLending);
    }

    private void requireBookAndStudent(BookLending bookLending) {
        if (bookLending.getBook() == null || bookLending.getBook().getId() == null) {
            throw new InvalidBookLendingException("Book ID is null");
        }

        if (bookLending.getStudent() == null || bookLending.getStudent().getId() == null) {
            throw new InvalidBookLendingException("Student ID is null");
        }
    }

    private Book getBookOrThrow(Integer bookId) {
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new BookNotFoundException(bookId);
        }
    }

    private Student getStudentOrThrow(Integer studentId) {
        Optional<Student> optional = studentRepository.findById(studentId);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new StudentNotFoundException(studentId);
        }
    }

    private void checkIfBookIsAlreadyLent(Integer bookId) {
        Optional<BookLending> optional = bookLendingRepository.findBookLendingByBookId(bookId).stream()
                .filter(bookLending -> bookLending.getStatus().equals(BookLendingStatus.LENT))
                .findAny();

        if (optional.isPresent()) {
            throw new BookAlreadyLentException(bookId);
        }
    }

}
