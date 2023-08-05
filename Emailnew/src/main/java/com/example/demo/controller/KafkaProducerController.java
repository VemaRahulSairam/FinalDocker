package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Email;
import com.example.demo.services.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

  @PostMapping("/produceMessage")
    public ResponseEntity<String> produceMessage(@RequestParam String recipientEmail,
                                                 @RequestParam String subject,
                                                 @RequestParam String content) {
       
     Email email = new Email();
       email.setRecipientEmail(recipientEmail);
        email.setSubject(subject);
        email.setContent(content);

       
        String emailMessage = convertEmailToString(email);

        
        kafkaProducerService.sendMessageToKafka(emailMessage);

        return ResponseEntity.ok("Message sent to Kafka successfully");
    }

    private String convertEmailToString(Email email) {
    ObjectMapper objectMapper = new ObjectMapper();
       String emailJson = null;
        try {
            emailJson = objectMapper.writeValueAsString(email);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return emailJson;
    }
}
