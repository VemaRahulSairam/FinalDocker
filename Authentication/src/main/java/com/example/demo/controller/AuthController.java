package com.example.demo.controller;

 

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 

import com.example.demo.entity.UserModel;
import com.example.demo.service.AuthService;
import com.example.demo.service.AuthProducer;

 

@RestController
@RequestMapping("/kafkaapp")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

 

    @Autowired
    private AuthProducer producer;

 

    @Autowired
    private AuthService authService;

 

    @PostMapping("/signup")
    public ResponseEntity<String> sendMessage(@RequestBody UserModel user) {
        // Publish the user object to the Kafka topic for authentication
        producer.publishToTopic(user.getStatus());
        // Assuming the authentication process is handled asynchronously by the Consumer class
        // Check the status after a certain time or through some notification mechanism
        // For this example, let's assume the authentication status is set by the Consumer after processing the message
        if ("authentication failed".equals(user.getStatus())) {
            return new ResponseEntity<String>("Authentication failed", HttpStatus.UNAUTHORIZED);
        } else if ("authentication successful".equals(user.getStatus())) {
            UserModel existingUser = authService.getUserByEmail(user.getEmail());

    }
        return new ResponseEntity<String>("Authentication successful", HttpStatus.OK);
    }

    

    @GetMapping("/userList")
    public ResponseEntity<List<UserModel>> getAllUserDetails() {
        List<UserModel> userList = authService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}

