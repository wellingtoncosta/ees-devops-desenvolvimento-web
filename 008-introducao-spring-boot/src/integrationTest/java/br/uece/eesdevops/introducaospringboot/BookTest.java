package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.introducaospringboot.domain.entity.Book;
import br.uece.eesdevops.introducaospringboot.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.util.NestedServletException;

import static br.uece.eesdevops.introducaospringboot.Fakes.fakeBookWithNoId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = IntroducaoSpringBootApplication.class)
@DisplayName("Runs all tests for book registration")
class BookTest {

    // region setup tests

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BookRepository repository;

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
        repository.deleteAllInBatch();
    }

    // endregion

    // region GET /books

    @Test
    @DisplayName("should get all books with no results")
    void should_get_all_books_with_no_results() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("should get all books with one result")
    void should_get_all_books_with_one_result() throws Exception {
        Book book = fakeBookWithNoId();

        book = repository.save(book);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(book.getId())))
                .andExpect(jsonPath("$[0].isbn", is(book.getIsbn())))
                .andExpect(jsonPath("$[0].title", is(book.getTitle())))
                .andExpect(jsonPath("$[0].author", is(book.getAuthor())));
    }

    @Test
    @DisplayName("should get all books with three result")
    void should_get_all_books_with_three_result() throws Exception {
        List<Book> books = new ArrayList<>();

        books.add(fakeBookWithNoId());
        books.add(fakeBookWithNoId());
        books.add(fakeBookWithNoId());

        books = repository.saveAll(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(books.get(0).getId())))
                .andExpect(jsonPath("$[0].isbn", is(books.get(0).getIsbn())))
                .andExpect(jsonPath("$[0].title", is(books.get(0).getTitle())))
                .andExpect(jsonPath("$[0].author", is(books.get(0).getAuthor())))
                .andExpect(jsonPath("$[1].id", is(books.get(1).getId())))
                .andExpect(jsonPath("$[1].isbn", is(books.get(1).getIsbn())))
                .andExpect(jsonPath("$[1].title", is(books.get(1).getTitle())))
                .andExpect(jsonPath("$[1].author", is(books.get(1).getAuthor())))
                .andExpect(jsonPath("$[2].id", is(books.get(2).getId())))
                .andExpect(jsonPath("$[2].isbn", is(books.get(2).getIsbn())))
                .andExpect(jsonPath("$[2].title", is(books.get(2).getTitle())))
                .andExpect(jsonPath("$[2].author", is(books.get(2).getAuthor())));
    }

    // endregion

    // region GET /books/{id}

    @Test
    @DisplayName("should get book for ID successfully")
    void should_get_a_book_for_id_successfully() throws Exception {
        Book book = fakeBookWithNoId();

        book = repository.save(book);

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId())))
                .andExpect(jsonPath("$.isbn", is(book.getIsbn())))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())));
    }

    @Test
    @DisplayName("should not get book for ID when it does not exist")
    void should_not_get_a_book_for_id_when_it_does_not_exist() throws Exception {
        Book book = fakeBookWithNoId();

        repository.save(book);

        int bookId = 9999;

        mockMvc.perform(get("/books/" + bookId))
                .andExpect(status().isNotFound());
    }

    // endregion

    // region POST /books

    @Test
    @DisplayName("should save a new book successfully")
    void should_save_new_book_successfully() throws Exception {
        Book book = mapper.readValue(Payloads.newBookRequest(), Book.class);

        MockHttpServletRequestBuilder request = post("/books")
                .content(Payloads.newBookRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.isbn", is(book.getIsbn())))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.publicationYear", is(book.getPublicationYear())));
    }

    @Test
    @DisplayName("should not save a new book when an error occurs")
    void should_not_save_new_book_when_an_error_occurs() throws Exception {
        Book book = mapper.readValue(Payloads.newBookRequest(), Book.class);

        repository.save(book);

        MockHttpServletRequestBuilder request = post("/books")
                .content(Payloads.newBookRequest())
                .contentType(MediaType.APPLICATION_JSON);

        assertThrows(
                NestedServletException.class,
                () -> mockMvc.perform(request),
                "ERROR: duplicate key value violates unique constraint \"book_isbn_key\"\n" +
                        "  Detalhe: Key (isbn)=(0321125215) already exists."
        );
    }

    //endregion

    // region PUT /books/{id}

    @Test
    @DisplayName("should update a book successfully")
    void should_update_a_book_successfully() throws Exception {
        Book book = mapper.readValue(Payloads.updateBookRequest(), Book.class);

        book = repository.save(book);

        MockHttpServletRequestBuilder request = put("/books/" + book.getId())
                .content(Payloads.updateBookRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId())))
                .andExpect(jsonPath("$.isbn", is(book.getIsbn())))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.publicationYear", is(book.getPublicationYear())));
    }

    @Test
    @DisplayName("should not update a book when it does not exist")
    void should_not_update_a_book_when_it_does_not_exist() throws Exception {
        MockHttpServletRequestBuilder request = put("/books/" + 9999)
                .content(Payloads.newBookRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    //endregion

}
