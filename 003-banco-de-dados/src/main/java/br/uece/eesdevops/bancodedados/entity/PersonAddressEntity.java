package br.uece.eesdevops.bancodedados.entity;

import javax.persistence.*;

@Entity
@Table(name = "person_address")
public class PersonAddressEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String country;

    @Column
    private String state;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String streetNumber;

}
