package br.uece.ees.springtesting.domain.model;

public enum TransactionType {
    CREDIT, DEBIT;

    public static TransactionType from(int value) {
        return switch (value) {
            case 0:
                yield CREDIT;
            case 1:
                yield DEBIT;
            default:
                throw new InvalidTransactionTypeException(value);
        };
    }
}
