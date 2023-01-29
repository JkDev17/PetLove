package com.jk.pets.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetsRepository extends JpaRepository<Pets,Integer>
{
    //@Query(value = "SELECT * FROM pets", nativeQuery = true)
    //public List<Pets> getAllRecordsPets();
}
