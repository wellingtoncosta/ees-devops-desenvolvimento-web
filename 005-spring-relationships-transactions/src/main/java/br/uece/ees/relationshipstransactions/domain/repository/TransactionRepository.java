package br.uece.ees.relationshipstransactions.domain.repository;

import br.uece.ees.relationshipstransactions.domain.model.Transaction;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
    List<Transaction> findAllByAccountId(Long accountId);
}
