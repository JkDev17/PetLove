package com.jk.breeder.rating.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BreedersRating
{
    @Id
    @SequenceGenerator(
            name = "breeders_rating_id_sequence",
            sequenceName = "breeders_rating_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "breeders_rating_id_sequence"
    )
    private int id;
    private int breederId;
    private int userId;
    private int rating;
}