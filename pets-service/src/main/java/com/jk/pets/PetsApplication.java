package com.jk.pets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PetsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PetsApplication.class, args);
    }
}