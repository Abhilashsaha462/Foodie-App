package com.niit.authenticationservice.AuthenticationService.service;

import com.niit.authenticationservice.AuthenticationService.domain.EmailDetails;
import com.niit.authenticationservice.AuthenticationService.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public String sendSimpleEmail(User user) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setText("Congratulations...!!! You Have Successfully Registered to Foodie App...!!!");
            mailMessage.setSubject("Foodie App Registration...!!!");
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...!!!";
        }catch (Exception e){
            return "Error while Sending Mail...!!!";
        }
    }

    @Override
    public String sendEmailWithAttachments(User user) {
        EmailDetails emailDetails = new EmailDetails();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setText(user.getUserName()+"Congratulations...!!! You Have Successfully Registered to Foodie App...!!!");
            mimeMessageHelper.setSubject("Foodie App Registration...!!!");
            FileSystemResource fileSystemResource = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully...!!!";
        }catch (MessagingException e){
            return "Error While Sending Mail...!!!";
        }
    }
}
