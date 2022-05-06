package com.example.bunjangv2;

import com.example.bunjangv2.trace.LogTrace;
import com.example.bunjangv2.trace.ThreadLocalLogTrace;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Bunjangv2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bunjangv2Application.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
