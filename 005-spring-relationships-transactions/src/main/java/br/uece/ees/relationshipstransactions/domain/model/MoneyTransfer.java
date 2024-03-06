package br.uece.ees.relationshipstransactions.domain.model;

import java.math.BigDecimal;

public record MoneyTransfer(Long accountSenderId, Long accountRecipientId, BigDecimal amount) {
}
