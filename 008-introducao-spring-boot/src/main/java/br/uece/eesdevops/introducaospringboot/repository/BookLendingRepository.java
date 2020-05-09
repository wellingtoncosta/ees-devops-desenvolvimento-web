package br.uece.eesdevops.introducaospringboot.repository;

import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLendingRepository extends JpaRepository<BookLending, Integer> {

    List<BookLending> findBookLendingByBookId(Integer bookId);

}
