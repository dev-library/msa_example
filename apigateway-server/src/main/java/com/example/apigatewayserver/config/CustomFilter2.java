package com.example.apigatewayserver.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter2 extends AbstractGatewayFilterFactory<CustomFilter2.Config> {

    public CustomFilter2(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            // pre filter는 그냥 내부에 실행문으로 작성하면 돌아갑니다.
            System.out.println("Custom2 pre filter : " + request.getId());

            // post filter는 return구문 속에 코드를 작성해서 만듭니다.
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("Custom2 post filter : " + response.getStatusCode());
            }));
        };
    }
    public static class Config {

    }
}
