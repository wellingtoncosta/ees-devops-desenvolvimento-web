package br.uece.eesdevops.introducaospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaveBooksInBatchService {

    @Autowired
    private BookRepository repository;

    @Transactional
    public void execute(List<Book> books) {
        for (Book book : books) {
            repository.save(book);
        }
    }

}
