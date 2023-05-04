package com.eoi.springwebsecurity.scheduling.modelos;

import com.eoi.springwebsecurity.email.EmailService;
import com.eoi.springwebsecurity.websockets.messages.PrivateMessage;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAsincrono implements Runnable{

    private EmailService emailService;
    private String message;
    private String destinatario;
    private String remitente;


    @Override
        public void run() {
        try {
            emailService.sendEmail("mail.alejandro.teixeira@gmail.com",
                    "",
                    "");
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
