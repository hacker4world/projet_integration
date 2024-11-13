package com.topone.projet_integration.Servicemail;

import com.topone.projet_integration.DTO.EmployeeDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.SecureRandom;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;




    public void sendVerificationEmail(String email,String name,String code) throws MessagingException {
        try {
            //Nsn3o l email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //parametre mta3 email
            helper.setTo(email);
            helper.setSubject("Account Verification");
            helper.setFrom("hamdijbeli1920@gmail.com");



            //thymleaf yaml e template
            Context context = new Context();
            context.setVariable("code", code);
            context.setVariable("name", name);
//narj3o etemplate b thymleaf
            String htmlContent = templateEngine.process("email", context);

            //contenuu mta body html
            helper.setText(htmlContent, true);

           //envoie mt3 email
            mailSender.send(message);
        } catch (Exception e) {

            e.printStackTrace();
            throw new MessagingException("Failed to send verification email", e);
        }
    }

    // random code

}
