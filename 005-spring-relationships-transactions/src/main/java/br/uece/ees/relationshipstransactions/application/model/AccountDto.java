package br.uece.ees.relationshipstransactions.application.model;

import br.uece.ees.relationshipstransactions.domain.model.Account;

import java.math.BigDecimal;
import java.util.List;

public record AccountDto(Long id, Long number, Integer type, BigDecimal balance, List<TransactionDto> transactions) {
    public static AccountDto from(Account account) {
        return new AccountDto(
                account.getId(),
                account.getNumber(),
                account.getType().ordinal(),
                account.getBalance(),
                TransactionDto.from(account.getTransactions())
        );
    }
}
