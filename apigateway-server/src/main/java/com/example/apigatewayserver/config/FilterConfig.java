package com.example.apigatewayserver.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator gatewayLocator(RouteLocatorBuilder builder){
        return builder.routes()// 라우팅 정보 등록 시작지점
                .route(r ->
                        r.path("/first-service/**")
                            .filters(f -> f.addRequestHeader("fsreqh", "fshreqv")
                                    .addResponseHeader("fsresh", "fsreshv"))
                            .uri("http://localhost:8001/")
                ) // 개별 라우팅 정보를 등록하는 route() 메서드
                .route(r ->
                        r.path("/second-service/**")
                                .filters(f -> f.addRequestHeader("ssreqh", "sshreqv")
                                        .addResponseHeader("ssresh", "ssreshv"))
                                .uri("http://localhost:8002/")
                ) // 개별 라우팅 정보를 등록하는 route() 메서드
                .build();// 라우팅 정보 등록 종료지점
    }

}
