package com.jk.breeders.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BreedersRepository extends JpaRepository<Breeders,Integer>
{
    @Query(value = "SELECT * FROM breeders WHERE full_name = ?1", nativeQuery = true)
     Breeders getBreederDataByFullName(String fullName);

    @Query(value = "SELECT id FROM breeders WHERE  full_name = ?1", nativeQuery = true)
     Integer getIdByFullName(String fullName);

}