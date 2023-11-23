package com.emailsender.emailsender.service;

import org.springframework.http.ResponseEntity;

import com.emailsender.emailsender.domain.EmailDetails;

public interface EmailService {
    
    String sendSimpleEMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);

    ResponseEntity<?> testSend();
}
