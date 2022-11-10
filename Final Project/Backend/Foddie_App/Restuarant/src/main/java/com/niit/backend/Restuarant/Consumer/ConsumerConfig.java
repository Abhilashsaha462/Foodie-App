package com.niit.backend.Restuarant.Consumer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    @Bean
    public Jackson2JsonMessageConverter producerConverter(){
        return new Jackson2JsonMessageConverter();
    }
}

