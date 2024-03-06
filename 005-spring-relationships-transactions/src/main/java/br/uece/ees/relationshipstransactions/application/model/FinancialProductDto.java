package br.uece.ees.relationshipstransactions.application.model;

import br.uece.ees.relationshipstransactions.domain.model.FinancialProduct;

import java.util.List;
import java.util.stream.Collectors;

public record FinancialProductDto(Long id, String name) {
    public static FinancialProductDto from(FinancialProduct financialProduct) {
        return new FinancialProductDto(
                financialProduct.getId(),
                financialProduct.getName()
        );
    }

    public static List<FinancialProductDto> from(List<FinancialProduct> financialProducts) {
        return financialProducts.stream()
                .map(FinancialProductDto::from)
                .collect(Collectors.toList());
    }
}
