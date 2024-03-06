package br.uece.ees.relationshipstransactions.domain.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionTypeException extends RuntimeException {
    public InvalidTransactionTypeException(int value) {
        super("TransactionType for value " + value + " is not valid");
    }
}
