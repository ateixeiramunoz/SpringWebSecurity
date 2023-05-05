package com.eoi.springwebsecurity.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String recipientEmail, String link, String cuerpoMensaje)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("cuentadepruebaspringboot@gmail.com", "Soporte EOI");
        helper.setTo(recipientEmail);

        String subject = "Tienes un aviso";

        String content = "<p>Hola,</p>"
                + "<p>Tienes un aviso de la app</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


}
