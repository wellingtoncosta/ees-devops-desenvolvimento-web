package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLendingStatus;
import br.uece.eesdevops.introducaospringboot.domain.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class Fakes {

    private Fakes() { }

    static Book fakeBook() {
        Book book = new Book();
        book.setId(1);
        book.setIsbn(generateFakeIsbn());
        book.setTitle("Fake Book");
        book.setAuthor("Fake Author");
        book.setPublicationYear(1999);
        return book;
    }

    static Book fakeBookWithNoId() {
        Book book = fakeBook();
        book.setId(null);
        return book;
    }

    private static String generateFakeIsbn() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            isbn.append(random.nextInt(10));
        }

        return isbn.toString();
    }

    static Student fakeStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("Fake Student");
        student.setEmail("fake.student@fakeschool.com");
        student.setEnrollment(generateFakeEnrollment());
        return student;
    }

    static Student fakeStudentWithNoId() {
        Student student = fakeStudent();
        student.setId(null);
        return student;
    }

    private static String generateFakeEnrollment() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            isbn.append(random.nextInt(10));
        }

        return isbn.toString();
    }

    static BookLending fakeBookLending() {
        BookLending bookLending = new BookLending();
        bookLending.setId(1);
        bookLending.setBook(fakeBook());
        bookLending.setStudent(fakeStudent());
        bookLending.setStatus(BookLendingStatus.LENT);
        return bookLending;
    }

    static BookLending fakeReturnedBookLending() {
        BookLending bookLending = new BookLending();
        bookLending.setId(1);
        bookLending.setBook(fakeBook());
        bookLending.setStudent(fakeStudent());
        bookLending.setStatus(BookLendingStatus.RETURNED);
        return bookLending;
    }

    static BookLending fakeBookLendingWithNoId() {
        BookLending bookLending = fakeBookLending();
        bookLending.setId(null);
        return bookLending;
    }

    static BookLending fakeBookLending(Book book, Student student) {
        BookLending bookLending = new BookLending();
        bookLending.setId(1);
        bookLending.setBook(book);
        bookLending.setStudent(student);
        bookLending.setStatus(BookLendingStatus.LENT);
        return bookLending;
    }

    static BookLending fakeBookLendingWithNoId(Book book, Student student) {
        BookLending bookLending = fakeBookLending(book, student);
        bookLending.setId(null);
        return bookLending;
    }

    static BookLending fakeBookLendingWithNoId(Book book, Student student, BookLendingStatus status) {
        BookLending bookLending = fakeBookLending(book, student);
        bookLending.setId(null);
        bookLending.setStatus(status);
        return bookLending;
    }

    static List<BookLending> fakeBookLendingList() {
        List<BookLending> bookLendings = new ArrayList<>();
        bookLendings.add(fakeBookLending());
        return bookLendings;
    }

    static List<BookLending> fakeBookLendingListWithLentAndReturned() {
        List<BookLending> bookLendings = new ArrayList<>();
        bookLendings.add(fakeBookLending());
        bookLendings.add(fakeReturnedBookLending());
        return bookLendings;
    }

}
