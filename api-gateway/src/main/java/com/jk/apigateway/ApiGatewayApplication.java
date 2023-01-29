package com.jk.apigateway;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class ApiGatewayApplication

{
    public static void main(String[] args)
    {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    // Solves the nested exception is org.springframework.core.io.buffer.DataBufferLimitException: Exceeded limit on max bytes per JSON object: 262144
    @Bean
    public WebClient webClient()
    {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .exchangeStrategies(strategies)
                .build();
    }

    //Implementing Reactive Circuit Breaker Using Resilience4j
    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> customerServiceCusomtizer()
    {
        return factory ->
                factory.configure(builder -> builder
                        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "customer-service");
    }

    //Solves the api gateway issue (500 err when calling localhost port and not each microservice port)
    @Bean
    public HttpClient httpClient()
    {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}


