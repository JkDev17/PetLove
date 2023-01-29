package com.jk.breeders.model;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Breeders
{
    @Id
    @SequenceGenerator(
            name = "owner_id_sequence",
            sequenceName = "owner_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_id_sequence"
    )
    private int id;

    @Column( nullable = false)
    private String fullName;

    @Column( nullable = false)
    private String password;

    @Column( nullable = false)
    private String country;

    @Column( nullable = false)
    private String city;

    @Column( nullable = false)
    private int petId;

    @Column( nullable = false)
    private byte[] image;
}