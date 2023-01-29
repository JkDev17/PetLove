package com.jk.pets.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PetsDto
{
    private int id;

    private String name;

    private String type;

    private String breed;

    private String  image;

    private int age;

    boolean isFullyVaccinated;



    public PetsDto(Pets pets)
    {
        this.id = pets.getId();
        this.name = pets.getName();
        this.type = pets.getType();
        this.breed = pets.getBreed();
        this.age = pets.getAge();
        this.isFullyVaccinated = pets.isFullyVaccinated();
        this.sex = pets.getSex();
    }

    enum sex
    {
        MALE,
        FEMALE
    }

    private Pets.sex sex;
}