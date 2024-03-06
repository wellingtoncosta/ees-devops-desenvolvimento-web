package br.uece.ees.relationshipstransactions.application.model;

import br.uece.ees.relationshipstransactions.domain.model.Account;
import br.uece.ees.relationshipstransactions.domain.model.AccountType;

public record NewAccountDto(Integer type) {
    public Account toAccount() {
        var account = new Account();
        account.setType(AccountType.from(type));
        return account;
    }
}
