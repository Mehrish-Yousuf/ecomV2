package com.ecomv2.mailservice.services;

import com.ecomv2.mailservice.DTO.Mail;

public interface MailService {
    public void sendEmail(Mail mail);
}
