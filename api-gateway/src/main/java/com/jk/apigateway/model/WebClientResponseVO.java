package com.jk.apigateway.model;

import com.jk.breeders.model.BreedersDataDto;

import com.jk.pets.model.PetsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WebClientResponseVO
{
    private BreedersDataDto breedersDataDto;
    private PetsDto petsDto;

    public WebClientResponseVO(PetsDto petsDto, BreedersDataDto breedersDataDto)
    {
        this.petsDto = petsDto;
        this.breedersDataDto = breedersDataDto;
    }
}