package br.uece.eesdevops.introducaospringboot.web;

import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookLendingNotFoundException;
import br.uece.eesdevops.introducaospringboot.domain.service.ChangeBookLendingStatusService;
import br.uece.eesdevops.introducaospringboot.domain.service.LendBookService;
import br.uece.eesdevops.introducaospringboot.repository.BookLendingRepository;
import br.uece.eesdevops.introducaospringboot.web.entity.NewBookLending;
import br.uece.eesdevops.introducaospringboot.web.entity.PatchBookLendingStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/books-lending")
public class BookLendingController {

    private final BookLendingRepository repository;
    private final LendBookService lendBookService;
    private final ChangeBookLendingStatusService changeBookLendingStatusService;

    public BookLendingController(
            BookLendingRepository repository,
            LendBookService lendBookService,
            ChangeBookLendingStatusService changeBookLendingStatusService
    ) {
        this.repository = repository;
        this.lendBookService = lendBookService;
        this.changeBookLendingStatusService = changeBookLendingStatusService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookLending>> getAll() {
        List<BookLending> lendings = repository.findAll();
        return ResponseEntity.ok(lendings);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookLending> getById(@PathVariable Integer id) {
        Optional<BookLending> lending = repository.findById(id);
        if (!lending.isPresent()) {
            throw new BookLendingNotFoundException(id);
        } else {
            return ResponseEntity.ok(lending.get());
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookLending> save(@RequestBody NewBookLending request) {
        BookLending saved = lendBookService.execute(request.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PatchMapping(
            value = "/{id}/status",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookLending> patchStatus(
            @PathVariable Integer id,
            @RequestBody PatchBookLendingStatus request
    ) {
        BookLending changed = changeBookLendingStatusService.execute(id, request.toDomain());
        return ResponseEntity.ok(changed);
    }

}
