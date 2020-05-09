package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLending;
import br.uece.eesdevops.introducaospringboot.domain.entity.BookLendingStatus;
import br.uece.eesdevops.introducaospringboot.domain.entity.Student;
import br.uece.eesdevops.introducaospringboot.repository.BookLendingRepository;
import br.uece.eesdevops.introducaospringboot.repository.BookRepository;
import br.uece.eesdevops.introducaospringboot.repository.StudentRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBookLendingWithNoId;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBookWithNoId;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeStudentWithNoId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = IntroducaoSpringBootApplication.class)
@DisplayName("Runs all tests for book lending")
class BookLendingTest {

    // region setup tests

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookLendingRepository bookLendingRepository;

    private static EmbeddedDatabase database;

    @BeforeAll
    static void init() throws IOException {
        database = new EmbeddedDatabase();
    }

    @AfterAll
    static void tearDown() throws IOException {
        database.stopServer();
    }

    @BeforeEach
    void beforeEach() {
        bookLendingRepository.deleteAllInBatch();
        studentRepository.deleteAllInBatch();
        bookRepository.deleteAllInBatch();
    }

    // endregion

    // region GET /books-lending

    @Test
    @DisplayName("should get all books lending with no results")
    void should_get_all_books_lending_with_no_results() throws Exception {
        mockMvc.perform(get("/books-lending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Transactional
    @DisplayName("should get all books with one result")
    void should_get_all_books_with_one_result() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());
        Student student = studentRepository.save(fakeStudentWithNoId());
        BookLending bookLending = bookLendingRepository.save(
                fakeBookLendingWithNoId(book, student)
        );

        mockMvc.perform(get("/books-lending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(bookLending.getId())));
    }

    @Test
    @Transactional
    @DisplayName("should get all books with three result")
    void should_get_all_books_with_three_result() throws Exception {
        List<BookLending> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Book book = bookRepository.save(fakeBookWithNoId());
            Student student = studentRepository.save(fakeStudentWithNoId());
            list.add(fakeBookLendingWithNoId(book, student));
        }

        bookLendingRepository.saveAll(list);

        mockMvc.perform(get("/books-lending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    // endregion

    // region GET /books-lending/{id}

    @Test
    @Transactional
    @DisplayName("should get a book lending for ID successfully")
    void should_get_a_book_lending_for_id_successfully() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());
        Student student = studentRepository.save(fakeStudentWithNoId());
        BookLending bookLending = bookLendingRepository.save(
                fakeBookLendingWithNoId(book, student)
        );

        mockMvc.perform(get("/books-lending/" + bookLending.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookLending.getId())));
    }

    @Test
    @Transactional
    @DisplayName("should not get book for ID when it does not exist")
    void should_not_get_a_book_for_id_when_it_does_not_exist() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());
        Student student = studentRepository.save(fakeStudentWithNoId());
        bookLendingRepository.save(
                fakeBookLendingWithNoId(book, student)
        );

        mockMvc.perform(get("/books-lending/" + 9999))
                .andExpect(status().isNotFound());
    }

    // endregion

    // region POST /books-lending

    @Test
    @Transactional
    @DisplayName("should lent a book successfully")
    void should_lent_a_book_successfully() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());
        Student student = studentRepository.save(fakeStudentWithNoId());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("book", book.getId());
        jsonObject.put("student", student.getId());

        MockHttpServletRequestBuilder request = post("/books-lending")
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(BookLendingStatus.LENT.toString())));
    }

    @Test
    @DisplayName("should not lent a book when book or student is null")
    void should_not_lent_a_book_when_book_or_student_is_null() throws Exception {
        JSONObject jsonObject = new JSONObject();

        mockMvc.perform(
                post("/books-lending")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        jsonObject.put("book", null);
        jsonObject.put("student", null);

        mockMvc.perform(
                post("/books-lending")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        jsonObject.put("book", 1);
        jsonObject.put("student", null);

        mockMvc.perform(
                post("/books-lending")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("should not lent a book when book does not exist")
    void should_not_lent_a_book_when_book_does_not_exist() throws Exception {
        Student student = studentRepository.save(fakeStudentWithNoId());

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("book", 1);
        jsonObject.put("student", student.getId());

        mockMvc.perform(
                post("/books-lending")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should not lent a book when student does not exist")
    void should_not_lent_a_book_when_student_does_not_exist() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("book", book.getId());
        jsonObject.put("student", 9999);

        mockMvc.perform(
                post("/books-lending")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @DisplayName("should not lent a book when book is already lent")
    void should_not_lent_a_book_when_book_is_already_lent() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());
        Student student = studentRepository.save(fakeStudentWithNoId());
        bookLendingRepository.save(fakeBookLendingWithNoId(book, student));

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("book", book.getId());
        jsonObject.put("student", student.getId());

        mockMvc.perform(
                post("/books-lending")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    // endregion

    // region PATCH /books-lending{id}/status

    @Test
    @Transactional
    @DisplayName("should change book lending status to returned successfully")
    void should_change_book_lending_status_to_returned_successfully() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());
        Student student = studentRepository.save(fakeStudentWithNoId());
        BookLending bookLending = bookLendingRepository.save(
                fakeBookLendingWithNoId(book, student)
        );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", BookLendingStatus.RETURNED.toString());

        mockMvc.perform(
                patch("/books-lending/" + bookLending.getId() + "/status")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookLending.getId())))
                .andExpect(jsonPath("$.status", is(BookLendingStatus.RETURNED.toString())));
    }

    @Test
    @Transactional
    @DisplayName("should change book lending status to lent successfully")
    void should_change_book_lending_status_to_lent_successfully() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());
        Student student = studentRepository.save(fakeStudentWithNoId());
        BookLending bookLending = bookLendingRepository.save(
                fakeBookLendingWithNoId(book, student, BookLendingStatus.RETURNED)
        );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", BookLendingStatus.LENT.toString());

        mockMvc.perform(
                patch("/books-lending/" + bookLending.getId() + "/status")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookLending.getId())))
                .andExpect(jsonPath("$.status", is(BookLendingStatus.LENT.toString())));
    }

    @Test
    @Transactional
    @DisplayName("should not change book lending status when it does not exist")
    void should_not_change_book_lending_status_when_it_does_exist() throws Exception {
        Book book = bookRepository.save(fakeBookWithNoId());

        bookLendingRepository.save(
                fakeBookLendingWithNoId(
                        book,
                        studentRepository.save(fakeStudentWithNoId())
                )
        );

        BookLending bookLending = bookLendingRepository.save(
                fakeBookLendingWithNoId(
                        book,
                        studentRepository.save(fakeStudentWithNoId()),
                        BookLendingStatus.RETURNED
                )
        );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", BookLendingStatus.LENT.toString());

        mockMvc.perform(
                patch("/books-lending/" + bookLending.getId() + "/status")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    // endregion

}
