package com.topone.projet_integration.services;

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

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendVerificationEmail(String email, String name, String emailVerificationCode) throws MessagingException {
        try {

            // creating email instance
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // setting the email title, sender and recipient
            helper.setTo(email);
            helper.setSubject("Email Verification - Somezzo");
            helper.setFrom("hamdijbeli1920@gmail.com");

            Context context = new Context();

            // choosing the HTML template for the email and setting the name and code variables
            context.setVariable("code", emailVerificationCode);
            context.setVariable("name", name);

            String htmlContent = templateEngine.process("email-verification", context);

            helper.setText(htmlContent, true);

            // send the email
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MessagingException("Failed to send verification email", e);
        }
    }

    public void sendPasswordResetEmail(String email, String name, String passwordResetCode) throws MessagingException {
        try {

            // creating email instance
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // setting the email title, sender and recipient
            helper.setTo(email);
            helper.setSubject("Password reset - Somezzo");
            helper.setFrom("hamdijbeli1920@gmail.com");

            Context context = new Context();

            // choosing the HTML template for the email and setting the name and code variables
            context.setVariable("code", passwordResetCode);
            context.setVariable("name", name);

            String htmlContent = templateEngine.process("password-reset", context);

            helper.setText(htmlContent, true);

            // send the email
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MessagingException("Failed to send verification email", e);
        }
    }

}
