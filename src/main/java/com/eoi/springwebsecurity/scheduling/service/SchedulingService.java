package com.eoi.springwebsecurity.scheduling.service;

import com.eoi.springwebsecurity.coreapp.repositories.UserRepository;
import com.eoi.springwebsecurity.email.EmailService;
import com.eoi.springwebsecurity.scheduling.entities.Cita;
import com.eoi.springwebsecurity.scheduling.modelos.EmailAsincrono;
import com.eoi.springwebsecurity.scheduling.modelos.NotificacionAsincrona;
import com.eoi.springwebsecurity.scheduling.repository.CitaRepository;
import jakarta.validation.constraints.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


@Component
@Log4j2
public class SchedulingService {


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    EmailService emailService;

    /**
     * Este metodo crea una cita para un cliente en BD.
     *
     * @param cita
     *
     * @return
     */
    public Cita crearCita(Cita cita) {
        //TODO - CAMBIAR PARA QUE COJA EL USUARIO REAL
        cita.setUsuario(userRepository.findById(1L).get());
        Cita citaSaved = citaRepository.save(cita);

        if (Boolean.TRUE.equals(citaSaved.getNotificar())) {
            crearAviso(citaSaved);
        }

        if (Boolean.TRUE.equals(citaSaved.getEnviarEmail())) {
            crearAvisoMail(citaSaved);
        }

        return citaSaved;
    }


/*
    public void crearAviso(Cita cita) {
        String expresionCron = "";

        LocalDateTime fechaAviso =
                cita.getFecha()
                        .minusMinutes(cita.getNumeroMinutos())
                        .minusHours(cita.getNumeroHoras())
                        .minusDays(cita.getNumeroDias());

        expresionCron =
                "0 "
                        + fechaAviso.getMinute() + " "
                        + fechaAviso.getHour() + " "
                        + fechaAviso.getDayOfMonth() + " "
                        + fechaAviso.getMonth() + " "
                        + "?"
        ;
        log.info(expresionCron);

        CronTrigger cronTrigger = new CronTrigger(expresionCron);
        threadPoolTaskScheduler.schedule(
                new NotificacionAsincrona(simpMessagingTemplate,
                        "Tarea Con Cron",
                        "mail.alejandro.teixeira@gmail.com",
                        "mail.alejandro.teixeira@gmail.com"),
                cronTrigger
        );


    }
*/


    public void crearAviso(Cita cita)
    {
        LocalDateTime fechaAviso =
            cita.getFecha()
                    .minusMinutes(cita.getNumeroMinutos())
                    .minusHours(cita.getNumeroHoras())
                    .minusDays(cita.getNumeroDias());

        LocalDateTime fechaActual =
                cita.getFecha()
                        .minusMinutes(cita.getNumeroMinutos())
                        .minusHours(cita.getNumeroHoras())
                        .minusDays(cita.getNumeroDias());

        Duration duracionEspera = Duration.between(fechaActual, fechaAviso);

        //Creo un temporizador a partir de la hora actual
        PeriodicTrigger periodicTrigger = new PeriodicTrigger( 1L, TimeUnit.MINUTES);

        //Aplicamos el delay de inicio igual al tiempo que hay de diferencia entre la primera notificacion y la fecha
        // actual
        periodicTrigger.setInitialDelay(duracionEspera);

        threadPoolTaskScheduler.schedule(
                new NotificacionAsincrona(simpMessagingTemplate,
                        "Tarea repetitiva con PeriodicTrigger",
                        "mail.alejandro.teixeira@gmail.com",
                        "mail.alejandro.teixeira@gmail.com"),
                periodicTrigger
        );



    }


    public void crearAvisoMail(Cita cita) {

        String expresionCron = "";

        LocalDateTime fechaAviso =
                cita.getFecha()
                        .minusMinutes(cita.getNumeroMinutos())
                        .minusHours(cita.getNumeroHoras())
                        .minusDays(cita.getNumeroDias());

        expresionCron =
                "0 "
                        + fechaAviso.getMinute() + " "
                        + fechaAviso.getHour() + " "
                        + fechaAviso.getDayOfMonth() + " "
                        + fechaAviso.getMonth() + " "
                        + "?"
        ;
        log.info(expresionCron);

        CronTrigger cronTrigger = new CronTrigger(expresionCron);
        threadPoolTaskScheduler.schedule(
                new EmailAsincrono(
                        emailService,
                        "mail.alejandro.teixeira@gmail.com",
                        "mail.alejandro.teixeira@gmail.com",
                        "mail.alejandro.teixeira@gmail.com"),
                cronTrigger
        );

    }


}
