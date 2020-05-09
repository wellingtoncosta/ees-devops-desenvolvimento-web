package br.uece.eesdevops.introducaospringboot.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookLendingNotFoundException extends RuntimeException {

    public BookLendingNotFoundException(Integer id) {
        super("Book lending for ID " + id + " does not exist.");
    }

}
