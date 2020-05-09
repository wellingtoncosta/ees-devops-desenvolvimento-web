package br.uece.eesdevops.introducaospringboot.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Integer id) {
        super("Student for ID " + id + " does not exist.");
    }

}
