package com.jk.breeders.web;

import com.jk.breeders.model.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/breeders")
public class BreedersController
{
    private final BreedersRepository breedersRepository;
    private final BreedersDataDtoRepository breedersDataDtoRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("hi")
    public String hi()
    {
        return "hi from breeders";
    }

    @PostMapping ("/getAllBreeders")
    public HashMap<String,List<String>> getAllBreeders()
    {
        HashMap   <String,List<String>>  map= new HashMap<>();
        ArrayList <String> images = new ArrayList<>();
        ArrayList <String> fullNames;
        ArrayList <String> cities;
        ArrayList <String> countries;

        List<Breeders> breeders = breedersRepository.findAll();

        List <byte[]> imgRaw = breeders.stream()
                .map(Breeders::getImage)
                .collect(Collectors.toList());

        for (byte[] img:imgRaw )
        {
            String newImage = new String(img);
            images.add(newImage);
        }

        fullNames  = (ArrayList<String>) breeders.stream().map(Breeders::getFullName).collect(Collectors.toList());
        cities = (ArrayList<String>) breeders.stream().map(Breeders::getCity).collect(Collectors.toList());
        countries = (ArrayList<String>) breeders.stream().map(Breeders::getCountry).collect(Collectors.toList());

        map.put("images", images);
        map.put("fullNames",fullNames);
        map.put("cities", cities);
        map.put("countries",countries);

        return map;
    }

    @GetMapping("/getBreedersData")
    public List<BreedersDataDto> getBreedersData() { return breedersDataDtoRepository.getBreedersData();}

    @PostMapping("/saveBreeder")
    @ResponseStatus(HttpStatus.CREATED)
    public int createBreeder(@Valid @RequestBody BreederDto breederDto)
    {
        log.info("/saveBreeder was called");

        byte [] image = breederDto.getImage().getBytes(StandardCharsets.UTF_8);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(breederDto.getPassword());

        Breeders breeder = Breeders.builder()
                .image(image)
                .fullName(breederDto.getFullName())
                .petId(breederDto.getPetId())
                .country(breederDto.getCountry())
                .city(breederDto.getCity())
                .password(hashedPassword)
                .build();

        breedersRepository.save(breeder);
        return breeder.getId();
    }


    @GetMapping("/testBreederImage")
    public String testPetImage()
    {
        List<Breeders> petImageBytes = breedersRepository.findAll();
        List<byte[]> petImage =  petImageBytes.stream().map(Breeders::getImage).collect(Collectors.toList());

        byte[] imageRawBytes = petImage.get(3);
        return new String(imageRawBytes);
    }



    @GetMapping("/getBreederData")
    public BreederDto getBreederData(@RequestParam String fullName)
    {
        log.info("FullName as param:" + fullName);
        Breeders breeder = breedersRepository.getBreederDataByFullName(fullName);
        byte [] imageBytes = breeder.getImage();
        String image = new String(imageBytes);
        return BreederDto.builder()
                .id(breeder.getId())
                .fullName(fullName)
                .password(breeder.getPassword())
                .country(breeder.getCountry())
                .city(breeder.getCity())
                .petId(breeder.getPetId())
                .image(image).build();
    }

    @GetMapping("/getBreederIdFromFullName")
    public int getBreederIdFromFullName(@RequestParam String fullName)
    {
        log.info("FullName as parameter:" + fullName);
        return  breedersRepository.getIdByFullName(fullName);
    }
}