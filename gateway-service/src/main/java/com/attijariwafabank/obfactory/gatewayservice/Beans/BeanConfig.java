package com.attijariwafabank.obfactory.gatewayservice.Beans;

// import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
// import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
// import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // .route(r -> ((Object) r.path("/api/v1/realisations/**")
                //         .uri("lb://FIRST-SERVICE/"))
                //         .id("first-service"))
                .route(p -> p
                     .path("/users/**")
                     .uri("lb://USER-SERVICE"))
                     .build();
    }
    // @Bean
    // DiscoveryClientRouteDefinitionLocator dynam(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
    //     return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    // }
}

