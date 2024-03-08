package br.uece.ees.springtesting.application.model;

import br.uece.ees.springtesting.domain.model.Transaction;
import br.uece.ees.springtesting.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record NewTransactionDto(int type, BigDecimal amount, LocalDateTime date) {
    public Transaction toTransaction() {
        var transaction = new Transaction();
        transaction.setType(TransactionType.from(type));
        transaction.setAmount(amount);
        transaction.setDate(Objects.requireNonNullElseGet(date, LocalDateTime::now));
        return transaction;
    }
}
