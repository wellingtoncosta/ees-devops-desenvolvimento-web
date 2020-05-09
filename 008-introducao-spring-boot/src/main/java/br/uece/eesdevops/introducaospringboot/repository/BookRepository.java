package br.uece.eesdevops.introducaospringboot.repository;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> { }
