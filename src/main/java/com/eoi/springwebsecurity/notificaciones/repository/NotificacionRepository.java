package com.eoi.springwebsecurity.notificaciones.repository;

import com.eoi.springwebsecurity.notificaciones.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificacionRepository extends JpaRepository<Notificacion, String> {

    public List<Notificacion> findByUserToAndEstado(String userTo,String estado);

}