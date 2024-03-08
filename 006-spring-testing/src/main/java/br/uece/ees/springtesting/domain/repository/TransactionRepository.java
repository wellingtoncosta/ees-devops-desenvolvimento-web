package br.uece.ees.springtesting.domain.repository;

import br.uece.ees.springtesting.domain.model.Transaction;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
    List<Transaction> findAllByAccountId(Long accountId);
}
