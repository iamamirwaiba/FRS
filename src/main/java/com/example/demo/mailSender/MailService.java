package com.example.demo.mailSender;

import org.springframework.mail.javamail.JavaMailSender;

public interface MailService {
    public void sendSimpleMessage(
            String to, String subject, String text);
            public JavaMailSender getJavaMailSender();


}
