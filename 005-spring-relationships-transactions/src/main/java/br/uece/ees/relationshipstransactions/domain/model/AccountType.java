package br.uece.ees.relationshipstransactions.domain.model;

public enum AccountType {
    CHECKING,
    SAVINGS;

    public static AccountType from(int value) {
        if (value == 1) {
            return AccountType.SAVINGS;
        } else {
            return AccountType.CHECKING;
        }
    }
}
