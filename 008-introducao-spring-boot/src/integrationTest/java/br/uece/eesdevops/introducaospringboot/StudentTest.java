package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.introducaospringboot.domain.entity.Student;
import br.uece.eesdevops.introducaospringboot.repository.StudentRepository;
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

import static br.uece.eesdevops.introducaospringboot.Fakes.fakeStudentWithNoId;
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
@DisplayName("Runs all tests for student registration")
class StudentTest {

    // region setup tests

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StudentRepository repository;

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

    // region GET /students

    @Test
    @DisplayName("should get all students with no results")
    void should_get_all_students_with_no_results() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("should get all students with one result")
    void should_get_all_students_with_one_result() throws Exception {
        Student student = fakeStudentWithNoId();

        student = repository.save(student);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(student.getId())))
                .andExpect(jsonPath("$[0].name", is(student.getName())))
                .andExpect(jsonPath("$[0].email", is(student.getEmail())))
                .andExpect(jsonPath("$[0].enrollment", is(student.getEnrollment())));
    }

    @Test
    @DisplayName("should get all students with three result")
    void should_get_all_students_with_three_result() throws Exception {
        List<Student> students = new ArrayList<>();

        students.add(fakeStudentWithNoId());
        students.add(fakeStudentWithNoId());
        students.add(fakeStudentWithNoId());

        students = repository.saveAll(students);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(students.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(students.get(0).getName())))
                .andExpect(jsonPath("$[0].email", is(students.get(0).getEmail())))
                .andExpect(jsonPath("$[0].enrollment", is(students.get(0).getEnrollment())))
                .andExpect(jsonPath("$[1].id", is(students.get(1).getId())))
                .andExpect(jsonPath("$[1].name", is(students.get(1).getName())))
                .andExpect(jsonPath("$[1].email", is(students.get(1).getEmail())))
                .andExpect(jsonPath("$[1].enrollment", is(students.get(1).getEnrollment())))
                .andExpect(jsonPath("$[2].id", is(students.get(2).getId())))
                .andExpect(jsonPath("$[2].name", is(students.get(2).getName())))
                .andExpect(jsonPath("$[2].email", is(students.get(2).getEmail())))
                .andExpect(jsonPath("$[2].enrollment", is(students.get(2).getEnrollment())));
    }

    // endregion

    // region GET /students/{id}

    @Test
    @DisplayName("should get student for ID successfully")
    void should_get_a_student_for_id_successfully() throws Exception {
        Student student = fakeStudentWithNoId();

        student = repository.save(student);

        mockMvc.perform(get("/students/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(student.getId())))
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())))
                .andExpect(jsonPath("$.enrollment", is(student.getEnrollment())));
    }

    @Test
    @DisplayName("should not get student for ID when it does not exist")
    void should_not_get_a_student_for_id_when_it_does_not_exist() throws Exception {
        Student student = fakeStudentWithNoId();

        repository.save(student);

        int studentId = 9999;

        mockMvc.perform(get("/students/" + studentId))
                .andExpect(status().isNotFound());
    }

    // endregion

    // region POST /students

    @Test
    @DisplayName("should save a new student successfully")
    void should_save_new_student_successfully() throws Exception {
        Student student = mapper.readValue(Payloads.newStudentRequest(), Student.class);

        MockHttpServletRequestBuilder request = post("/students")
                .content(Payloads.newStudentRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())))
                .andExpect(jsonPath("$.enrollment", is(student.getEnrollment())));
    }

    @Test
    @DisplayName("should not save a new student when an error occurs")
    void should_not_save_new_student_when_an_error_occurs() throws Exception {
        Student student = mapper.readValue(Payloads.newStudentRequest(), Student.class);

        repository.save(student);

        MockHttpServletRequestBuilder request = post("/students")
                .content(Payloads.newStudentRequest())
                .contentType(MediaType.APPLICATION_JSON);

        assertThrows(
                NestedServletException.class,
                () -> mockMvc.perform(request),
                "ERROR: duplicate key value violates unique constraint \"student_enrollment_key\"\n" +
                        "  Detalhe: Key (isbn)=(032112521512) already exists."
        );
    }

    //endregion

    // region PUT /students/{id}

    @Test
    @DisplayName("should update a student successfully")
    void should_update_a_student_successfully() throws Exception {
        Student student = mapper.readValue(Payloads.updateStudentRequest(), Student.class);

        student = repository.save(student);

        MockHttpServletRequestBuilder request = put("/students/" + student.getId())
                .content(Payloads.updateStudentRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(student.getId())))
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())))
                .andExpect(jsonPath("$.enrollment", is(student.getEnrollment())));
    }

    @Test
    @DisplayName("should not update a student when it does not exist")
    void should_not_update_a_student_when_it_does_not_exist() throws Exception {
        MockHttpServletRequestBuilder request = put("/students/" + 9999)
                .content(Payloads.newBookRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    //endregion

}
