package br.uece.eesdevops.introducaospringboot;

import org.springframework.stereotype.Component;

@Component
public class Mailer {

    public void sendEmailToNewBooksMailingList(Book book) {
        System.out.println("Sending e-mail to new books mailing list...");
        System.out.println("The new book is " + book.getTitle() + " by " + book.getAuthor());
    }

}
