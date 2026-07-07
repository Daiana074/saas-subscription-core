package com.daiana.saas_subscription_core.repository;

import com.daiana.saas_subscription_core.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio encargado de la persistencia y auditoría de los pagos de las facturas.
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    /**
     * Query Method para recuperar todas las facturas asociadas a un contrato de suscripción.
     */
    List<Factura> findBySuscripcionId(Long suscripcionId);
}