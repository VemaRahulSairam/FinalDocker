package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;
import com.google.gson.Gson;

@Service
public class LoginProducer {

	public static final String topic="logintopic";
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemp;
	
	//convert model object to string and then  you have to send the message
	public void publishToTopic(User user) {
        Gson gson = new Gson();
        UserModel userModel=new UserModel(1, null, user.getEmail(),user.getPassword() ,"REQUEST", null);
        String userJson = gson.toJson(userModel,UserModel.class);
        
        System.out.println("publishing to topic  " + topic);
        this.kafkaTemp.send(topic, userJson);
        System.out.println(userJson);
    }

}

























//public void publishToTopic(String email,String password) {
//System.out.println("publishing to topic  "+topic);
//this.kafkaTemp.send(topic,email,password);
//}

//@Autowired
//private KafkaTemplate<String, User> kafkaTemplate;
//
//public void publishToTopic(User user) {
//    kafkaTemplate.send(topic, user);
//}
