package br.uece.eesdevops.introducaospringboot.web.entity;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.entity.Student;

public class NewBookLending {

    private Integer book;
    private Integer student;

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    public BookLending toDomain() {
        Book book = new Book();
        book.setId(this.book);

        Student student = new Student();
        student.setId(this.student);

        BookLending bookLending = new BookLending();
        bookLending.setBook(book);
        bookLending.setStudent(student);

        return bookLending;
    }

}
