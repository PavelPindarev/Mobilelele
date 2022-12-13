package com.example.mobilelele.service.impl;

import com.example.mobilelele.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendRegistrationEmail(String userEmail,
                                      String userName) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("mobilele@mobilele.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Welcome!");
            mimeMessageHelper.setText(generateMessageContent(userName), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String generateMessageContent(String userName) {
        Context ctx = new Context();
        ctx.setVariable("userName", userName);
        return templateEngine.process("email/registration", ctx);
    }
}
