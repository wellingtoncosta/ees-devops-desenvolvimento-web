package br.uece.eesdevops.bancodedados.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classroom")
public class ClassroomEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String schedule;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_student",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<StudentEntity> students;

}
