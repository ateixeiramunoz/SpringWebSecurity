package com.eoi.springwebsecurity.websockets.service;

import com.eoi.springwebsecurity.websockets.entities.Notificacion;
import com.eoi.springwebsecurity.websockets.entities.NotificacionRepository;
import com.eoi.springwebsecurity.websockets.messages.PrivateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


// 1.- Crear un objeto notificación en Base de datos
// 2.- Obtener el ID De la notificación para adjuntarlo al mensaje
// 3.- Al recibir el mensaje, procesaremos el contenido y obtendremos el ID
// 4.- En el metodo callback de la recepción, enviaremos el ID Del mensaje para indicar que se ha recibido.
// 5.- Desde el servidor, al recibir la confirmación de dicho mensaje, marcaremos la notificación a recibida.

@Service
public class MessagingService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public Notificacion crearNotificacion (PrivateMessage message) {

        Notificacion notificacion = new Notificacion();
        notificacion.setEstado("Pendiente");
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setUserTo(message.getTo());
        notificacion.setUserFrom(message.getFrom());
        notificacion.setMensaje(message.getText());
        notificacionRepository.save(notificacion);

        return notificacion;

    }

    public void EnviarMensajeSTOMP(PrivateMessage message)
    {

    }

}
