package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLendingStatus;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookAlreadyLentException;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookLendingNotFoundException;
import br.uece.eesdevops.introducaospringboot.domain.service.ChangeBookLendingStatusService;
import br.uece.eesdevops.introducaospringboot.repository.BookLendingRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBookLending;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBookLendingListWithLentAndReturned;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeReturnedBookLending;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Runs all tests for domain service class responsible for changing book lending status")
class ChangeBookLendingStatusServiceTest {

    private final BookLendingRepository repository =
            mock(BookLendingRepository.class);

    private ChangeBookLendingStatusService service;

    @BeforeEach
    void beforeEach() {
        service = new ChangeBookLendingStatusService(repository);
    }

    @Test
    @DisplayName("should change book lending status to returned status successfully")
    void should_change_book_lending_status_to_returned_status_successfully() {
        BookLending bookLending = fakeBookLending();

        when(repository.findById(bookLending.getId()))
                .thenReturn(Optional.of(bookLending));

        when(repository.save(bookLending))
                .thenReturn(bookLending);

        BookLending changed = service.execute(bookLending.getId(), BookLendingStatus.RETURNED);

        assertEquals(BookLendingStatus.RETURNED, changed.getStatus());

        verify(repository).findById(bookLending.getId());
        verify(repository).save(bookLending);
    }

    @Test
    @DisplayName("should change book lending status to lent status successfully")
    void should_change_book_lending_status_to_lent_status_successfully() {
        BookLending bookLending = fakeReturnedBookLending();

        when(repository.findById(bookLending.getId()))
                .thenReturn(Optional.of(bookLending));

        when(repository.findBookLendingByBookId(bookLending.getBook().getId()))
                .thenReturn(new ArrayList<>());

        when(repository.save(bookLending))
                .thenReturn(bookLending);

        BookLending changed = service.execute(bookLending.getId(), BookLendingStatus.LENT);

        assertEquals(BookLendingStatus.LENT, changed.getStatus());

        verify(repository).save(bookLending);
        verify(repository).findById(bookLending.getId());
        verify(repository).findBookLendingByBookId(bookLending.getBook().getId());
    }

    @Test
    @DisplayName("should not change book lending status to same status")
    void should_not_change_book_lending_status_to_same_status() {
        BookLending bookLending = fakeBookLending();

        when(repository.findById(bookLending.getId()))
                .thenReturn(Optional.of(bookLending));

        BookLending changed = service.execute(bookLending.getId(), BookLendingStatus.LENT);

        assertEquals(BookLendingStatus.LENT, changed.getStatus());

        verify(repository).findById(bookLending.getId());
    }

    @Test
    @DisplayName("should not change book lending status to lent when the book was lent")
    void should_not_change_book_lending_status_to_lent_when_the_book_was_lent() {
        BookLending bookLending = fakeReturnedBookLending();

        when(repository.findById(bookLending.getId()))
                .thenReturn(Optional.of(bookLending));

        when(repository.findBookLendingByBookId(bookLending.getBook().getId()))
                .thenReturn(fakeBookLendingListWithLentAndReturned());

        assertThrows(
                BookAlreadyLentException.class,
                () -> service.execute(bookLending.getId(), BookLendingStatus.LENT),
                "Book for ID " + bookLending.getBook().getId() + " is already lent."
        );

        verify(repository).findById(bookLending.getId());
        verify(repository).findBookLendingByBookId(bookLending.getBook().getId());
    }

    @Test
    @DisplayName("should not change book lending status when book lending does not exist")
    void should_not_change_book_lending_status_when_book_lending_does_not_exist() {
        BookLending bookLending = fakeBookLending();

        when(repository.findById(bookLending.getId()))
                .thenReturn(Optional.empty());

        assertThrows(
                BookLendingNotFoundException.class,
                () -> service.execute(bookLending.getId(), BookLendingStatus.LENT),
                "Book lending for ID " + bookLending.getId() + " does not exist."
        );

        verify(repository).findById(bookLending.getId());
    }

}
