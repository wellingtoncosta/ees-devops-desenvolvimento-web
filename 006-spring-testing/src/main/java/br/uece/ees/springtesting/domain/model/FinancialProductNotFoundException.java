package br.uece.ees.springtesting.domain.model;

public class FinancialProductNotFoundException extends RuntimeException {
    public FinancialProductNotFoundException(Long id) {
        super("Unable to find financial product for id " + id);
    }
}
