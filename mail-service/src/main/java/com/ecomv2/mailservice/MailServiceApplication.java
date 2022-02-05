package com.ecomv2.mailservice;

import com.ecomv2.mailservice.DTO.Mail;
import com.ecomv2.mailservice.services.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MailServiceApplication {

    public static void main(String[] args) {

        Mail mail = new Mail();
        mail.setMailFrom("mehrish_yousuf@yahoo.com");
        mail.setMailTo("mehrish_yousuf@gmail.com");
        mail.setMailSubject("Spring Boot - Email Example");
        mail.setMailContent("Learn How to send Email using Spring Boot!!");

        ApplicationContext ctx = SpringApplication.run(MailServiceApplication.class, args);
        MailService mailService = (MailService) ctx.getBean("mailService");
        mailService.sendEmail(mail);


    }
}
