package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;



@SpringBootApplication
@EnableKafka
public class EmailnewApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailnewApplication.class, args);
    }

    
    }



