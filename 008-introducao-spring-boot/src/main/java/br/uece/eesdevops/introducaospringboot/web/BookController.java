package br.uece.eesdevops.introducaospringboot.web;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookNotFoundException;
import br.uece.eesdevops.introducaospringboot.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = repository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getById(@PathVariable Integer id) {
        Optional<Book> book = repository.findById(id);
        if (!book.isPresent()) {
            throw new BookNotFoundException(id);
        } else {
            return ResponseEntity.ok(book.get());
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> save(@RequestBody Book book) {
        Book saved = repository.save(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping(
            value = "/{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) {
        Optional<Book> bookFromDb = repository.findById(id);
        if (!bookFromDb.isPresent()) {
            throw new BookNotFoundException(id);
        } else {
            book.setId(id);
            Book updated = repository.save(book);
            return ResponseEntity.ok(updated);
        }
    }

}
