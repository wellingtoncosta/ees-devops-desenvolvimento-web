package br.uece.ees.springtesting.domain.repository;

import br.uece.ees.springtesting.domain.model.Account;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ListCrudRepository<Account, Long> {
    boolean existsByNumber(Long number);
}
