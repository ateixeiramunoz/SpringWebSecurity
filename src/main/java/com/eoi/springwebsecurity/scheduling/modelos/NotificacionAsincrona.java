package com.eoi.springwebsecurity.scheduling.modelos;

import com.eoi.springwebsecurity.websockets.messages.PrivateMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionAsincrona implements Runnable{

    private SimpMessagingTemplate simpMessagingTemplate;
    private String message;
    private String destinatario;
    private String remitente;


    @Override
        public void run() {

            PrivateMessage privateMessage = new PrivateMessage();
            privateMessage.setFrom("mail.alejandro.teixeira@gmail.com");
            privateMessage.setTo("mail.alejandro.teixeira@gmail.com");
            privateMessage.setText("Hola que tal. Esta notificación es asíncrona");
            simpMessagingTemplate.convertAndSendToUser(privateMessage.getTo(), "/specific", privateMessage);

        }

}
