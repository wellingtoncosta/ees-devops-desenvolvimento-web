package br.uece.ees.springtesting.domain.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(String email) {
        super("User with email '" + email + "' already exist.");
    }
}
