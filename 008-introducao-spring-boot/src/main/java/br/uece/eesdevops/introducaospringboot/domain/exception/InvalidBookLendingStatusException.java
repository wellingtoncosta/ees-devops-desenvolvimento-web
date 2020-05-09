package br.uece.eesdevops.introducaospringboot.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBookLendingStatusException extends RuntimeException {

    public InvalidBookLendingStatusException(String value) {
        super("Book lending status '" + value + "' is not valid.");
    }

}
