package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;
    @Value("${hostname}")
    private String hostname;

    public void send(String emailTo, String subject, User user){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String message  = String.format(
                "Welcome to Linuxmix, click on the activation link http://"+hostname+"/activate/"+user.getActivationCode()
                );

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
