package com.example.demo.service;

 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserModel;
import com.example.demo.repository.AuthRepository;
import com.google.gson.Gson;

 

@Service
public class AuthConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthConsumer.class);


    @Autowired
    private AuthRepository authRepository;
 
    @Autowired
    private AuthProducer authproducer;

    @KafkaListener(topics = "logintopic", groupId = "group")
    public void consumeFromTopic(String message) {
        Gson gson = new Gson();
        UserModel user = gson.fromJson(message, UserModel.class);

 

        if (isValidUser(user)) {
            LOGGER.info("Consumed message: " + user.getEmail() + " - " + user.getPassword());

 

            UserModel existingUser = authRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
            if (existingUser != null) {
                LOGGER.info("Authentication successful: User with the email already exists.");
                // Set the status as "authentication successful"
                user.setStatus("OK");
            } else {
                LOGGER.error("Authentication failed: User with the email does not exist.");
                // Set the status as "authentication failed"
                user.setStatus("KO");
            }

 

            String updatedMessage = gson.toJson(user,UserModel.class); // Convert the updated User object back to JSON
            authproducer.publishToTopic(updatedMessage);
 

            // Publish the updated message back to the Kafka topic
            LOGGER.info("Publishing updated message: " + updatedMessage);
            // Here, you would call the KafkaTemplate to publish the updated message to the topic.
            // For simplicity, we'll just log it.
            System.out.println("Publishing updated message: " + updatedMessage);
        } else {
            LOGGER.error("Error: Invalid email or password");
            // You can handle the error message in any way you prefer, e.g., throwing an exception, logging, etc.
        }
    }

 

    // Add a method to validate the user's email and password
    private boolean isValidUser(UserModel user) {
        String email = user.getEmail();
        String password = user.getPassword();
        return email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }
}