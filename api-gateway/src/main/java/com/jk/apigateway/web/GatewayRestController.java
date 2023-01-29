package com.jk.apigateway.web;

import com.jk.apigateway.model.WebClientResponseVO;
import com.jk.breeders.model.BreederDto;
import com.jk.breeders.model.BreedersDataDto;
import com.jk.pets.model.PetsDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/api/gateway")
@Slf4j
@RequiredArgsConstructor
public class GatewayRestController {

    @Autowired
    WebClient webClient;

    private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;


    @GetMapping("/getRatings")
    public Mono<Integer> getRatingsForFullName(@RequestParam("fullName") String fullName)
    {
        return    webClient.get()
                .uri("/breeders//getBreederIdFromFullName?fullName="+ fullName)
                .retrieve()
                .bodyToFlux(Integer.class).single()
                .flatMap(integer ->  webClient.get()
                        .uri(uriBuilder -> uriBuilder
                        .path("/breeder-rating-service/getAvgRatings")
                        .queryParam("userId",integer)
                        .build())
                        .retrieve()
                        .bodyToMono(Integer.class))
                        .transform(it ->
                        {
                            log.info("Circuit breaker activated for method getRatingsForFullName");
                            ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customer-service");
                            return rcb.run(it, throwable -> Mono.just(5));
                        });
    }



    @PostMapping("/collectData")
    public Flux<WebClientResponseVO> collectBreederAndPetData()
    {
        Flux<PetsDto> pets = getAllPets();
        Flux<BreedersDataDto> breeders = getAllBreeders();
        return Flux.zip(pets,breeders,WebClientResponseVO::new)
                .transform(it ->
                {
                    log.info("Circuit breaker activated for method collectBreederAndPetData");
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customer-service");
                    return rcb.run(it, throwable-> Flux.just(new WebClientResponseVO()));
                });
    }

    private Flux<BreedersDataDto> getAllBreeders()
    {
        log.info("Calling getBreedersData");

        return webClient.get()
                .uri("/breeders/getBreedersData")
                .retrieve()
                .bodyToFlux(BreedersDataDto.class)
                .transform(it ->
                {
                    log.info("Circuit breaker activated for method getAllBreeders");
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customer-service");
                    return rcb.run(it, throwable-> Flux.just(new BreedersDataDto()));
                });
    }

    public Flux<PetsDto> getAllPets()
    {
        log.info("Calling getAllRecordsPets");

        return webClient.get()
                .uri("/pets/getAllRecordsPets")
                .retrieve()
                .bodyToFlux(PetsDto.class)
                .transform(it ->
                {
                    log.info("Circuit breaker activated for method getAllPets");
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customer-service");
                    return rcb.run(it, throwable-> Flux.just(new PetsDto()));
                });
    }


    @GetMapping("/getBreederData")
    public Mono<BreederDto> getBreederData()
    {
        return webClient.get()
                .uri("/breeder/getBreedersData")
                .retrieve()
                .bodyToMono(BreederDto.class)
                .transform(it ->
                {
                    log.info("Circuit breaker activated for method getBreederData");
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customer-service");
                    return rcb.run(it, throwable-> Mono.just(new BreederDto()));
                });
    }

    @RequestMapping("/saveWebSession")
    public Mono<Void> saveWebSession(@RequestParam String fullName, WebSession ws, ServerHttpResponse response)
    {
        log.info("Called saveWebSession with fullName " + fullName);
        ws.getAttributes().put("fullName", fullName);
        log.info("FullName " + ws.getAttributes().get("fullName"));
        log.info("Web Session id: " + ws.getId());

        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/gateway/breeder"));
        return response.setComplete();
    }


    @RequestMapping("/getWebSession")
    public String getWebSession(WebSession ws)
    {
        log.info("FullName:" + ws.getAttributes().get("fullName"));
        Optional <String> fullName = Optional.ofNullable(ws.getAttribute("fullName"));
        return fullName.orElse("Carlos Silva");
    }
}