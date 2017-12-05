package com.powerrangers.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

    SimpleMailMessage createResetEmail(String to, String token) {
        SimpleMailMessage msg = new SimpleMailMessage();

        StringBuilder builder = new StringBuilder();
        builder.append("To reset your password, please follow the link below:\n");
        builder.append("http://localhost:8080/users/reset?token=");
        builder.append(token);
        String text = builder.toString();

        msg.setFrom("ua.powerrangers@gmail.com");
        msg.setTo(to);
        msg.setSubject("PowerRangers.ua password reset");
        msg.setText(text);
        return msg;
    }
}
