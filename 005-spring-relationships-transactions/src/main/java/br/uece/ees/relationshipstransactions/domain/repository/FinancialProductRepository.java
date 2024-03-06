package br.uece.ees.relationshipstransactions.domain.repository;

import br.uece.ees.relationshipstransactions.domain.model.FinancialProduct;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialProductRepository extends ListCrudRepository<FinancialProduct, Long> {
}
