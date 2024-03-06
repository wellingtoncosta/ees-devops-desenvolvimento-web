package br.uece.ees.relationshipstransactions.application.model;

import br.uece.ees.relationshipstransactions.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TransactionDto(Long id, Integer type, BigDecimal amount, LocalDateTime date) {
    public static TransactionDto from(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getType().ordinal(),
                transaction.getAmount(),
                transaction.getDate()
        );
    }

    public static List<TransactionDto> from(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionDto::from)
                .collect(Collectors.toList());
    }
}
