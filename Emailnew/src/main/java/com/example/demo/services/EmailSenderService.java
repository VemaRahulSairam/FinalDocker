package com.example.demo.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.model.Email;

@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;

    // Inject the provided JavaMailSenderImpl instance in the constructor
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public boolean sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getRecipientEmail());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());

        try {
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
