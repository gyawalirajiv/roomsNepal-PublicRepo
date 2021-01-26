package com.gyawalibros.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    private JavaMailSender mainSender;


    @Autowired
    public EmailService(JavaMailSender mainSender) {
        this.mainSender = mainSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        mainSender.send(simpleMailMessage);
    }
}
