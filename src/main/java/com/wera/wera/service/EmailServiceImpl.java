package com.wera.wera.service;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{
    
private JavaMailSender javaMailSender;

    @Override
    public void sendEmailWithToken(String userEmail, String link)throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Join Project Team Invitation";

        String text = "Click the link to join the project team "+ link;

        helper.setSubject(subject);
        helper.setText(text);
        helper.setTo(userEmail);

        try {
            javaMailSender.send(mimeMessage);
            
        } catch (Exception e) {
            throw new MailSendException("Failed to send email");
        }
        
    }
}