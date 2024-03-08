package br.uece.ees.springtesting.application.model;

import br.uece.ees.springtesting.domain.model.MoneyTransfer;

import java.math.BigDecimal;

public record MoneyTransferDto(Long accountSenderId, Long accountRecipientId, BigDecimal amount) {
    public MoneyTransfer toMoneyTransfer() {
        return new MoneyTransfer(
                accountSenderId,
                accountRecipientId,
                amount
        );
    }
}
