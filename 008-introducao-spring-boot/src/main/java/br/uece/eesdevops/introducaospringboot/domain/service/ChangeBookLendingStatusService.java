package br.uece.eesdevops.introducaospringboot.domain.service;

import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLendingStatus;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookAlreadyLentException;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookLendingNotFoundException;
import br.uece.eesdevops.introducaospringboot.repository.BookLendingRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeBookLendingStatusService {

    private final BookLendingRepository repository;

    public ChangeBookLendingStatusService(BookLendingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BookLending execute(Integer bookLendingId, BookLendingStatus newStatus) {
        Optional<BookLending> optional = repository.findById(bookLendingId);

        if (optional.isPresent()) {
            return changeStatus(optional.get(), newStatus);
        } else {
            throw new BookLendingNotFoundException(bookLendingId);
        }
    }

    private BookLending changeStatus(BookLending bookLending, BookLendingStatus newStatus) {
        if (bookLending.getStatus().equals(newStatus)) {
            return bookLending;
        }

        if (newStatus.equals(BookLendingStatus.LENT)) {
            checkIfHasBookLent(bookLending.getBook().getId());
        }

        bookLending.setStatus(newStatus);

        return repository.save(bookLending);
    }

    private void checkIfHasBookLent(Integer bookId) {
        Optional<BookLending> optional = repository.findBookLendingByBookId(bookId).stream()
                .filter(bookLending -> bookLending.getStatus().equals(BookLendingStatus.LENT))
                .findAny();

        if (optional.isPresent()) {
            throw new BookAlreadyLentException(bookId);
        }
    }

}
