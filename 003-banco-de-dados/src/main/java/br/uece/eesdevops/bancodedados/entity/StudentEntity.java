package br.uece.eesdevops.bancodedados.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String enrollment;

    @Column
    private String name;

    @Column
    private String email;

    @ManyToMany(mappedBy = "students")
    private List<ClassroomEntity> classes;

}
