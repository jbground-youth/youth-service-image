package com.youth.image.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySource("classpath:/aws.yml")
@PropertySources({
        @PropertySource("classpath:/aws.yml"),
        @PropertySource("classpath:/image.properties")
})
public class PropertyConfiguration {
}