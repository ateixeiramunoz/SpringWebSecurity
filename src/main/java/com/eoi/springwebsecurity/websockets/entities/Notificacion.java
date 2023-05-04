package com.eoi.springwebsecurity.websockets.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    private Long id;
    private String to;
    private String from;
    private LocalDateTime fecha;
    private String estado;
    private String mensaje;

}
