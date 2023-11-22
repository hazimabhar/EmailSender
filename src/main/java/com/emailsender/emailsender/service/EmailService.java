package com.emailsender.emailsender.service;

import com.emailsender.emailsender.domain.EmailDetails;

public interface EmailService {
    
    String sendSimpleEMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
