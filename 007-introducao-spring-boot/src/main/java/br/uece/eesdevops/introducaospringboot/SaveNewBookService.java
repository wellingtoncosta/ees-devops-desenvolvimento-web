package br.uece.eesdevops.introducaospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveNewBookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private Mailer mailer;

    public void execute(Book book) {
        repository.save(book);
        mailer.sendEmailToNewBooksMailingList(book);
    }

}
