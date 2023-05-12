package com.eoi.springwebsecurity.notificaciones.repository;

import com.eoi.springwebsecurity.notificaciones.Notificacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacionRepository extends PagingAndSortingRepository<Notificacion, String>,JpaRepository<Notificacion, String> {

    public List<Notificacion> findByUserToAndEstado(String userFrom, String estado);
    public Page<Notificacion> findByUserFromContainingIgnoreCaseAndUserToContainingIgnoreCaseOrderByFechaDesc(String userFrom,String userTo, Pageable pageable);

    public Page<Notificacion>  findByUserFromAndUserToLikeIgnoreCaseOrderByFechaDesc(String userFrom,String userTo, Pageable pageable);

    public Page<Notificacion> findByUserToOrderByFechaDesc(String userTo, Pageable pageable);
    public Page<Notificacion> findByUserToContainingIgnoreCaseOrderByFechaDesc (String userLike, Pageable pageable);




}