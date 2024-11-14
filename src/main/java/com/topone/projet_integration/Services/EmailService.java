package com.topone.projet_integration.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;




    public void sendVerificationEmail(String email,String name,String code) throws MessagingException {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Account Verification");
            helper.setFrom("hamdijbeli1920@gmail.com");

            Context context = new Context();
            context.setVariable("code", code);
            context.setVariable("name", name);

            String htmlContent = templateEngine.process("email", context);

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessagingException("Failed to send verification email", e);
        }
    }

}
