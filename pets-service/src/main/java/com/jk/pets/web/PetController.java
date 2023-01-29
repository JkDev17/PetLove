package com.jk.pets.web;

import com.jk.pets.model.PetsDto;
import com.jk.pets.model.PetsRepository;
import com.jk.pets.model.Pets;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController
{
    private final PetsRepository petsRepository;

    @GetMapping("/hi")
    public String hi()
    {
        return "hi from pets";
    }

    @GetMapping("/getAllRecordsPets")
    public List<PetsDto> getAllRecordsPets()
    {
        List<Pets> pets =  petsRepository.findAll();
        List<String> imagesList = new ArrayList<>();
        List<PetsDto> petsList = new ArrayList<>();

        //from byte[] to new String, so we can get the right representation of the image bytes
        for (Pets value : pets)
        {
            String image = new String(value.getImage());
            imagesList.add(image);
        }

        //copy all the data from all fields besides image, we will add images on next step
        List<PetsDto> petsDtoList = pets.stream()
        .map(PetsDto::new)
                .collect(Collectors.toList());

        //create new petDto list which will have images as Strings
        for( int i=0; i<petsDtoList.size(); i++)
        {
            petsList.add( new PetsDto(petsDtoList.get(i).getId(),
                                      petsDtoList.get(i).getName(),
                                      petsDtoList.get(i).getType(),
                                      petsDtoList.get(i).getBreed(),
                                      imagesList.get(i),
                                      petsDtoList.get(i).getAge(),
                                      petsDtoList.get(i).isFullyVaccinated(),
                                      petsDtoList.get(i).getSex()));
        }
        return petsList;
    }

    @PostMapping("/savePet")
    @ResponseStatus(HttpStatus.CREATED)
    public int createPet(@Valid @RequestBody PetsDto petsDto)
    {
        log.info("/savePet was called");
        log.info("Data is " + petsDto.isFullyVaccinated());

        byte[] image = petsDto.getImage().getBytes(StandardCharsets.UTF_8);

        Pets pet = Pets.builder()
                .age(petsDto.getAge())
                .name(petsDto.getName())
                .breed(petsDto.getBreed())
                .isFullyVaccinated(true)
                .type(petsDto.getType())
                .sex(petsDto.getSex())
                .image(image)
                .build();

        petsRepository.save(pet);
        return pet.getId();
    }

    @GetMapping("/testPetImage")
    public String testPetImage()
    {
        List<Pets> petImageBytes = petsRepository.findAll();
        List<byte[]> petImage =  petImageBytes.stream().map(Pets::getImage).collect(Collectors.toList());

        byte[] imageRawBytes = petImage.get(3);
        return new String(imageRawBytes);
    }
}
