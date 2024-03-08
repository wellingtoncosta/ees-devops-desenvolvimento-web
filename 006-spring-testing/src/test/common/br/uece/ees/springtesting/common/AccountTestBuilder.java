package br.uece.ees.springtesting.common;

import br.uece.ees.springtesting.domain.model.Account;
import br.uece.ees.springtesting.domain.model.Customer;
import br.uece.ees.springtesting.domain.model.Transaction;

public class AccountTestBuilder {
    private final Account account = new Account();

    public AccountTestBuilder withNumber(Long number) {
        account.setNumber(number);
        return this;
    }

    public AccountTestBuilder addTransaction(Transaction transaction) {
        account.getTransactions().add(transaction);
        return this;
    }

    public AccountTestBuilder ownedBy(Customer customer) {
        account.setOwner(customer);
        return this;
    }

    public Account build() {
        return account;
    }
}
