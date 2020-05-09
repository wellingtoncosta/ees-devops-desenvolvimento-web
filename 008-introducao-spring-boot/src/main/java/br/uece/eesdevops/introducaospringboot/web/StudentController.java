package br.uece.eesdevops.introducaospringboot.web;

import br.uece.eesdevops.introducaospringboot.domain.entity.Student;
import br.uece.eesdevops.introducaospringboot.domain.exception.StudentNotFoundException;
import br.uece.eesdevops.introducaospringboot.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = repository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getById(@PathVariable Integer id) {
        Optional<Student> student = repository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException(id);
        } else {
            return ResponseEntity.ok(student.get());
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student saved = repository.save(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping(
            value = "/{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody Student student) {
        Optional<Student> studentFromDb = repository.findById(id);
        if (!studentFromDb.isPresent()) {
            throw new StudentNotFoundException(id);
        } else {
            student.setId(id);
            Student updated = repository.save(student);
            return ResponseEntity.ok(updated);
        }
    }

}
