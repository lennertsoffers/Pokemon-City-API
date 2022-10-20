package com.lennertsoffers.pokemon_city_api.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The WebConfig class that is configured to the application needs
 */
public class WebConfig implements WebMvcConfigurer {
    /**
     * Add cors mappings to allow certain endpoints and methods
     * @param registry The CorsRegistry bean
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}