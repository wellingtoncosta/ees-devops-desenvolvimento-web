package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLendingStatus;
import br.uece.eesdevops.introducaospringboot.domain.entity.Student;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookAlreadyLentException;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookNotFoundException;
import br.uece.eesdevops.introducaospringboot.domain.exception.InvalidBookLendingException;
import br.uece.eesdevops.introducaospringboot.domain.exception.StudentNotFoundException;
import br.uece.eesdevops.introducaospringboot.domain.service.LendBookService;
import br.uece.eesdevops.introducaospringboot.repository.BookLendingRepository;
import br.uece.eesdevops.introducaospringboot.repository.BookRepository;
import br.uece.eesdevops.introducaospringboot.repository.StudentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBook;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBookLending;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBookLendingList;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeStudent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@DisplayName("Runs all tests for domain service class responsible for lending a book")
class LendBookServiceTest {

    private final BookRepository bookRepository =
            mock(BookRepository.class);

    private final StudentRepository studentRepository =
            mock(StudentRepository.class);

    private final BookLendingRepository bookLendingRepository =
            mock(BookLendingRepository.class);

    private LendBookService service;

    @BeforeEach
    void beforeEach() {
        service = new LendBookService(
                bookRepository,
                studentRepository,
                bookLendingRepository
        );
    }
    
    @Test
    @DisplayName("should lend a book successfully")
    void should_lend_a_book_successfully() {
        Book book = fakeBook();
        Student student = fakeStudent();
        BookLending bookLending = fakeBookLending(book, student);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(bookLendingRepository.save(bookLending)).thenReturn(bookLending);

        BookLending saved = service.execute(bookLending);

        assertEquals(book, saved.getBook());
        assertEquals(student, saved.getStudent());
        assertEquals(BookLendingStatus.LENT, saved.getStatus());

        verify(bookRepository).findById(book.getId());
        verify(studentRepository).findById(student.getId());
        verify(bookLendingRepository).save(bookLending);
    }

    @Test
    @DisplayName("should not lend a book when book or student is null")
    void should_not_lend_a_book_when_book_or_student_is_null() {
        assertThrows(
                InvalidBookLendingException.class,
                () -> service.execute(fakeBookLending(null, fakeStudent())),
                "Book ID is null."
        );

        Book bookWithNoId = fakeBook();
        bookWithNoId.setId(null);

        assertThrows(
                InvalidBookLendingException.class,
                () -> service.execute(fakeBookLending(bookWithNoId, fakeStudent())),
                "Book ID is null."
        );

        assertThrows(
                InvalidBookLendingException.class,
                () -> service.execute(fakeBookLending(fakeBook(), null)),
                "Student ID is null."
        );

        Student studentWithNoId = fakeStudent();
        studentWithNoId.setId(null);

        assertThrows(
                InvalidBookLendingException.class,
                () -> service.execute(fakeBookLending(fakeBook(), studentWithNoId)),
                "Student ID is null."
        );

        verifyNoInteractions(bookRepository);
        verifyNoInteractions(studentRepository);
        verifyNoInteractions(bookLendingRepository);
        verifyNoInteractions(bookLendingRepository);
    }

    @Test
    @DisplayName("should not lend a book when book does not exist")
    void should_not_lend_a_book_when_book_does_not_exist() {
        Book book = fakeBook();

        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

        assertThrows(
                BookNotFoundException.class,
                () -> service.execute(fakeBookLending(book, fakeStudent())),
                "Book for ID " + book.getId() + " does not exist."
        );
    }

    @Test
    @DisplayName("should not lend a book when student does not exist")
    void should_not_lend_a_book_when_student_does_not_exist() {
        Book book = fakeBook();
        Student student = fakeStudent();

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertThrows(
                StudentNotFoundException.class,
                () -> service.execute(fakeBookLending(fakeBook(), student)),
                "Student for ID " + student.getId() + " does not exist."
        );
    }

    @Test
    @DisplayName("should not lend a book when book is already lent")
    void should_not_lend_a_book_when_book_is_already_lent() {
        Book book = fakeBook();
        Student student = fakeStudent();

        when(bookRepository.findById(book.getId()))
                .thenReturn(Optional.of(book));

        when(studentRepository.findById(student.getId()))
                .thenReturn(Optional.of(student));

        when(bookLendingRepository.findBookLendingByBookId(book.getId()))
                .thenReturn(fakeBookLendingList());

        assertThrows(
                BookAlreadyLentException.class,
                () -> service.execute(fakeBookLending(fakeBook(), student)),
                "Book for ID " + book.getId() + " is already lent."
        );
    }
    
}
