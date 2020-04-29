package br.uece.eesdevops.bancodedados.entity;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_address_id")
    private PersonAddressEntity address;

}
