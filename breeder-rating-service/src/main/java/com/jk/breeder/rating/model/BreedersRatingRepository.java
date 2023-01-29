package com.jk.breeder.rating.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BreedersRatingRepository extends JpaRepository <BreedersRating, Integer>
{

    @Query(value = "SELECT avg(rating) FROM breeders_rating WHERE breeder_id = ?1", nativeQuery = true)
    public int getBreedersRatingsById(int id);
}