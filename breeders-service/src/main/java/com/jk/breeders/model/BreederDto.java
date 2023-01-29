package com.jk.breeders.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BreederDto
{
    private int id;

    private String fullName;

    private String password;

    private String country;

    private String city;

    private int petId;

    private String image;
}
