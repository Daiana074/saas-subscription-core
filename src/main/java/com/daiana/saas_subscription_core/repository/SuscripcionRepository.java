package com.daiana.saas_subscription_core.repository;

import com.daiana.saas_subscription_core.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio encargado de gestionar los contratos de suscripción de la base de datos.
 */
@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {

    /**
     * Query Method para buscar el historial de suscripciones de un usuario específico.
     * Java mapea esto automáticamente resolviendo el JOIN por atrás usando el objeto Usuario.
     */
    List<Suscripcion> findByUsuarioId(Long usuarioId);
}