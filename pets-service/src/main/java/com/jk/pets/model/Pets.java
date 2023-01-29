package com.jk.pets.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pets
{
    @Id
    @SequenceGenerator(
            name = "pet_id_sequence",
            sequenceName = "pet_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pet_id_sequence"
    )
    private int id;

    @Column( nullable = false)
    private String name;

    @Column( nullable = false)
    private String type;

    @Column( nullable = false)
    private String breed;

    @Column( nullable = false)
    private byte [] image;

    @Column( nullable = false)
    private int age;

    @Column( nullable = false)
    boolean isFullyVaccinated;

    enum sex
    {
        MALE,
        FEMALE
    }

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private sex sex;
}