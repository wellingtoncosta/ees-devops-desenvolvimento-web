package br.uece.ees.relationshipstransactions.domain.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class NoEnoughBalanceException extends RuntimeException {
    public NoEnoughBalanceException(Long accountId) {
        super("The account for id " + accountId + " has no enough balance");
    }
}
