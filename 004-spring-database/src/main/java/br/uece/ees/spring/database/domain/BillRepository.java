package br.uece.ees.spring.database.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends ListCrudRepository<BillEntity, Long> {

}
