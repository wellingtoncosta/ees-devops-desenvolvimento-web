package br.uece.ees.spring.database;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends ListCrudRepository<BillEntity, Long> {

}
