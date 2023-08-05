package com.example.demo.controller;

import java.util.List;

import org.dom4j.util.UserDataAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;
import com.example.demo.service.LoginConsumer;
import com.example.demo.service.LoginProducer;




@RestController
@RequestMapping("/kafkaapp")
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
	
	@Autowired
	LoginProducer producer;
	
	 @Autowired
	    LoginConsumer consumer;
	
	@PostMapping("/post")
	 public ResponseEntity<String> sendMessage(@RequestBody User user) {
		LoginConsumer.user=null;
	        producer.publishToTopic(user);
	        while(true) {
	        	if(LoginConsumer.user!=null) {
	        		break;
	        	}
	        }
	        if(LoginConsumer.user.getStatus().equals("OK")) {
	        	return new ResponseEntity<String>("OK", HttpStatus.OK);
	        }
	        else {
	        	return new ResponseEntity<String>("KO", HttpStatus.OK);
	        }
	}
	
	
	 @GetMapping("/messages")
	    public ResponseEntity<List<UserModel>> getConsumedMessages() {
	        List<UserModel> consumedMessages = consumer.getConsumedMessages();
	        return new ResponseEntity<List<UserModel>>(consumedMessages, HttpStatus.OK);
	    }
}


















//	 @GetMapping("/messages")
//	    public ResponseEntity<UserModel> getConsumedMessages() {
//	        UserModel consumedMessages = consumer.getConsumedMessages();
//	        return new ResponseEntity <UserModel>(consumedMessages, HttpStatus.OK);
//	    }
//	
	
//	 @GetMapping("/getUserById/{userId}")
//	    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
//	        User user = service.getUserById(userId);
//	        return new ResponseEntity<User>(user, HttpStatus.OK);
//	    }

//	@GetMapping("/userList")
//    public ResponseEntity<List<User>> getAllUserDetails() {
//      //  List<User> userList = userService.getAllUsers(); // Get all user details from the service
//       // return new ResponseEntity<>(userList, HttpStatus.OK);
//    }

















//@PostMapping(value="/post")
//public void sendMessage(@RequestParam("email") String email,@RequestParam("password") String password){
//	producer.publishToTopic(email,password);
//}

//@PostMapping(value = "/post")
//public void sendMessage(@RequestParam("email") String email, @RequestParam("password") String password) {
//    // Create a new User object
//    User user = new User();
//    user.setEmail(email);
//    user.setPassword(password);
//
//    // Publish the user object to the Kafka topic
//    producer.publishToTopic(user);
//}
