package com.jk.breeder.rating.web;

import com.jk.breeder.rating.model.BreedersRatingRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RequestMapping("breeder-rating-service")
@RestController
public class BreederRatingController
{

    BreedersRatingRepository breedersRatingRepository;

    @GetMapping("/getAvgRatings")
    public int getAvgRatings(@RequestParam("userId") int userId)
    {
        log.info("THE USER ID IS:"+ userId);
        return breedersRatingRepository.getBreedersRatingsById(userId);
    }



}