package com.jk.apigateway.web;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import javax.ws.rs.core.MediaType;

@Controller
@Slf4j
public class GatewayController
{
    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/static/breeder.html") final Resource indexHtml)
    {
        log.info("Returning the breeder.html page");
        return route(GET("/gateway/breeder"), request -> ok().contentType(org.springframework.http.MediaType.valueOf(MediaType.TEXT_HTML)).bodyValue(indexHtml));
    }

}