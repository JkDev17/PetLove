package com.jk.breeders.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BreedersDataDtoRepository extends JpaRepository <BreedersDataDto,Integer>
{
    @Query(value = "SELECT city, country, full_name, pet_id,id FROM breeders", nativeQuery = true)
    public List<BreedersDataDto> getBreedersData();
}
