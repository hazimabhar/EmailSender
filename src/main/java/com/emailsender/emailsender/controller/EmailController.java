package com.emailsender.emailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailsender.emailsender.domain.EmailDetails;
import com.emailsender.emailsender.service.EmailService;

@RestController
@RequestMapping("api/v1") 
public class EmailController {

    @Autowired private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailDetails emailDetails)
    {
        return emailService.sendSimpleEMail(emailDetails);
    }
    
    @PostMapping("/sendEmail/attachment")
    public String sendEmailWithAttachment(@RequestBody EmailDetails emailDetails){
        return emailService.sendMailWithAttachment(emailDetails);
    }
}
