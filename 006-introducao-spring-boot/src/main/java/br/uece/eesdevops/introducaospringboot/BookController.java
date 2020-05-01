package br.uece.eesdevops.introducaospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = repository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Optional<Book> book = repository.findById(id);
        if (!book.isPresent()) {
            throw new BookNotFoundException(id);
        } else {
            return ResponseEntity.ok(book.get());
        }
    }

}
