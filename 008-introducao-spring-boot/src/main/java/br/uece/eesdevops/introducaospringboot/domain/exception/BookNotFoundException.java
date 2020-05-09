package br.uece.eesdevops.introducaospringboot.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Integer id) {
        super("Book for ID " + id + " does not exist.");
    }

}
