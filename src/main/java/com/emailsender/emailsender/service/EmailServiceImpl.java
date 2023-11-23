package com.emailsender.emailsender.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailsender.emailsender.domain.Bug;
import com.emailsender.emailsender.domain.BugRepository;
import com.emailsender.emailsender.domain.EmailDetails;

import java.util.List;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired 
    
    private JavaMailSender javaMailSender;
    private BugRepository bugRepository;

    public EmailServiceImpl(JavaMailSender javaMailSender, BugRepository bugRepository) {

        this.javaMailSender = javaMailSender;
        this.bugRepository = bugRepository;
    }

    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendSimpleEMail(EmailDetails details) {  

        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully";     
        }

        catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true );
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            return "Success!";

        }

        catch (MessagingException e){
            return "Mail Sent Failed";
        }

    }

    @Override
    public ResponseEntity<?> testSend() {
        List<Bug> unsettleBugs = bugRepository.findByStatus("In Progress");

        if(unsettleBugs.isEmpty()){
            ResponseEntity.notFound();
        }
        else{

            for (Bug bug : unsettleBugs) {
                // System.out.println(bug.getBugId());
                // System.out.println(bug.getAssignedBy());
                // System.out.println(bug.getAssigneeTo());
                // System.out.println(bug.getAssigneeToEmail());
                // System.out.println(bug.getCustomerName());
                // System.out.println(bug.getSummary());


                String greeting = "Hello " + bug.getAssigneeTo() +",";
                String reminder = "This email is a remider that the bug with ID " + bug.getBugId() + " in project " + bug.getProjectName() + " is still not resolved.";
                String closure = "Thanks and Regards, \n" + bug.getAssignedBy();


                SimpleMailMessage mailMessage = new SimpleMailMessage();

                mailMessage.setSubject("Bug Notification Alert: "+ bug.getBugId() + " " + bug.getSummary());
                mailMessage.setFrom(sender);
                mailMessage.setTo(bug.getAssigneeToEmail());
                mailMessage.setText(
                    greeting+"\n\n"+
                    reminder+"\n\n"+
                    "Bug ID: " + bug.getBugId() + "\n"+
                    "Reported Date: " + bug.getReportedDate() + "\n"+
                    "Bug Summary: " + bug.getSummary() + "\n\n"+
                    "Kindly take necessary action to resolve it. \n\n"+
                    closure
                    );

                javaMailSender.send(mailMessage);

            }
        }


       return ResponseEntity.ok(unsettleBugs);
    }
}
