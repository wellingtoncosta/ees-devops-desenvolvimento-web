package br.uece.ees.springtesting.domain.repository;

import br.uece.ees.springtesting.domain.model.Customer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ListCrudRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
