package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.APRIL;

@Configuration
public class studentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student mariam = new Student(
                    "Olee",
                    "hellow@gmail.com",
                    LocalDate.of(2000, APRIL, 5));

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, APRIL, 5));
            repository.saveAll(
                    List.of(mariam, alex)
            );
        };

    }
}
