package br.uece.ees.springtesting.application.model;

import br.uece.ees.springtesting.domain.model.Account;
import br.uece.ees.springtesting.domain.model.AccountType;

public record NewAccountDto(Integer type) {
    public Account toAccount() {
        var account = new Account();
        account.setType(AccountType.from(type));
        return account;
    }
}
