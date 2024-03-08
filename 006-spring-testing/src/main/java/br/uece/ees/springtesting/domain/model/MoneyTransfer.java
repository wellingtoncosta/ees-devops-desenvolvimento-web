package br.uece.ees.springtesting.domain.model;

import java.math.BigDecimal;

public record MoneyTransfer(Long accountSenderId, Long accountRecipientId, BigDecimal amount) {
}
