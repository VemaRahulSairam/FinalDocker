package com.example.demo.service;

 

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UserModel;
import com.example.demo.repository.AuthRepository;
import com.google.gson.Gson;


@Service
public class AuthProducer {

 

    public static final String topic = "authtopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemp;

    @Autowired
    private AuthRepository authRepository;

    
    public  void publishToTopic(String user) {

            System.out.println("Publishing to topic: " + topic);
            this.kafkaTemp.send(topic, user);
        
    }

    
    private boolean isValidUser(UserModel user) {
        String email = user.getEmail();
        String password = user.getPassword();
        return email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }

    private boolean isUserExists(UserModel user) {
        String email = user.getEmail();
        String password=user.getPassword();
        UserModel existingUser = authRepository.findByEmailAndPassword(email,password);
        return existingUser != null;
    }


}

 