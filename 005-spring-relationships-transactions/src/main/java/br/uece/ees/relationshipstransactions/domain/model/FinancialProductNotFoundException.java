package br.uece.ees.relationshipstransactions.domain.model;

public class FinancialProductNotFoundException extends RuntimeException {
    public FinancialProductNotFoundException(Long id) {
        super("Unable to find financial product for id " + id);
    }
}
