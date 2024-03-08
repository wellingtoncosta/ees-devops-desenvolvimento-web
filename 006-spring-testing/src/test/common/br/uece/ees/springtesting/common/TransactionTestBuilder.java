package br.uece.ees.springtesting.common;

import br.uece.ees.springtesting.domain.model.Account;
import br.uece.ees.springtesting.domain.model.Transaction;
import br.uece.ees.springtesting.domain.model.TransactionType;

import java.math.BigDecimal;

public class TransactionTestBuilder {
    private final Transaction transaction = new Transaction();

    public TransactionTestBuilder type(TransactionType type) {
        transaction.setType(type);
        return this;
    }

    public TransactionTestBuilder amount(BigDecimal amount) {
        transaction.setAmount(amount);
        return this;
    }

    public TransactionTestBuilder account(Account account) {
        transaction.setAccount(account);
        return this;
    }

    public Transaction build() {
        return transaction;
    }
}
