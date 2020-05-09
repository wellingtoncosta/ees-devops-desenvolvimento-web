package br.uece.eesdevops.introducaospringboot.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyLentException extends RuntimeException {

    public BookAlreadyLentException(Integer id) {
        super("Book for ID " + id + " is already lent.");
    }

}
