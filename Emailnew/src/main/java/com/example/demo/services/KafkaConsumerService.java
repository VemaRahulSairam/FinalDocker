package com.example.demo.services;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.example.demo.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

 

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

 

@Service
public class KafkaConsumerService {

 

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

 

    @Autowired
    private EmailSenderService emailSenderService;

 

    private JavaMailSenderImpl mailSender;

 

    @Value("${spring.mail.host}")
    private String mailHost;

 

    @Value("${spring.mail.port}")
    private int mailPort;

 

    @Value("${spring.mail.username}")
    private String mailUsername;

 

    @Value("${spring.mail.password}")
    private String mailPassword;

 

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

 

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

 

    @PostConstruct
    private void init() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", mailSmtpAuth);
        properties.setProperty("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        mailSender.setJavaMailProperties(properties);
    }

 

    @KafkaListener(topics = "loginNotificationReq")
    public void consume(String message) {
        Gson gson = new Gson();
        try {
            Email target = gson.fromJson(message, Email.class);
            logger.info("Message received ->" + message);
            boolean emailSent = emailSenderService.sendEmail(target);

            target.setEmailSent(emailSent); 

//            String updatedTarget=gson.toJson(target,Email.class);
//            emailSenderService.sendEmail(updatedTarget);
          
 
            if (emailSent) {
                logger.info("Email sent successfully");
            } else {
                logger.info("Failed to send email");
            }
            
        } catch (JsonSyntaxException ex) {
            logger.info("consumer conversion" + ex.getMessage());
        } catch (Exception e) {
           
            logger.error("Error while processing email: " + e.getMessage());
        }
    }
}