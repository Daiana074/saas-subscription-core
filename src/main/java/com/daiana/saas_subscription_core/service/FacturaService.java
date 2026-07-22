package com.daiana.saas_subscription_core.service;

import com.daiana.saas_subscription_core.model.Factura;
import com.daiana.saas_subscription_core.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    /**
     * Obtiene todas las facturas registradas en la base de datos.
     */
    public List<Factura> listarTodas() {
        return facturaRepository.findAll();
    }

    /**
     * Obtiene las facturas vinculadas a una suscripción específica.
     */
    public List<Factura> listarPorSuscripcion(Long suscripcionId) {
        return facturaRepository.findBySuscripcionId(suscripcionId);
    }

    /**
     * Busca una factura por su ID.
     */
    public Factura buscarPorId(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró la factura con ID " + id));
    }

    /**
     * Permite cambiar el estado de pago de una factura (ej: de PENDIENTE a PAGADA o ANULADA).
     */
    @Transactional
    public Factura actualizarEstadoPago(Long facturaId, String nuevoEstado) {
        Factura factura = buscarPorId(facturaId);
        factura.setEstadoPago(nuevoEstado);
        return facturaRepository.save(factura);
    }
}