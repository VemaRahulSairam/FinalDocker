package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Email;
import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;
import com.google.gson.Gson;

@Service
public class LoginConsumer {
	
	  private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 public static UserModel user=null;
	public static final String LOGIN_NOTIFICATION_TOPIC="loginNotificationReq";
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemp;
	private List<UserModel> consumedMessages = new ArrayList<>();
	

	//get the model object from the string
	@KafkaListener(topics="authtopic", groupId="group")
	public void consumeFromTopic(String message) {
		Gson gson = new Gson();
//       message="{'email':'', 'password':''}";
		this.user=gson.fromJson(message,UserModel.class);
		if(user.getStatus() != null && user.getStatus().equals("OK")) {
			
			//loginNotion object and send this to email topic
			//profile
			Email email=new Email(user.getEmail(),"Login Notification","successfully logged in",false);
			String emailJson=gson.toJson(email,Email.class);
			 this.kafkaTemp.send(LOGIN_NOTIFICATION_TOPIC, emailJson);
			 
		}else {
            logger.info("Failed to send email");
        }
		System.out.println("consumed message "+ user.getEmail() + " - " + user.getPassword() + " - "+user.getName()+" -"+user.getRole());
		consumedMessages.add(user);
	
	}
	
	public List<UserModel> getConsumedMessages() {
        return consumedMessages;
    }

	
	@KafkaListener(topics="loginNotificationResp", groupId="group")
	public void loginNotificationResponse(String message) {
		Gson gson = new Gson();
		Email email=gson.fromJson(message, Email.class);
		if(email.isEmailSent())
			logger.info("Email sent successfully to "+ email.getRecipientEmail());
		else {
			
		}
	}	
}















//@KafkaListener(topics = "topic", groupId = "group")
//public void consumeUser(User user) {
//  // Process the received User object and save it to the database or perform any desired action
//  System.out.println("Received User: " + user.getEmail() + " - " + user.getPassword());
//  // Your code to save the User entity to the database goes here
//}