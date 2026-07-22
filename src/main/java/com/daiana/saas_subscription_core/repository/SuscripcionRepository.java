package com.daiana.saas_subscription_core.repository;

import com.daiana.saas_subscription_core.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {

    List<Suscripcion> findByUsuarioId(Long usuarioId);

    // AGREGAR ESTA LÍNEA (Consulta rápida para saber si ya tiene una activa):
    boolean existsByUsuarioIdAndEstado(Long usuarioId, String estado);
}