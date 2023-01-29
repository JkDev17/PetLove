package com.jk.breeders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BreedersApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BreedersApplication.class, args);
    }
}
