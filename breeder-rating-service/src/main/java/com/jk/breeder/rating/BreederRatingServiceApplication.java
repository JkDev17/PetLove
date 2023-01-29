package com.jk.breeder.rating;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication

public class BreederRatingServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BreederRatingServiceApplication.class, args);
    }
}