package com.daiana.saas_subscription_core.service;

import com.daiana.saas_subscription_core.dto.SuscripcionRequestDTO;
import com.daiana.saas_subscription_core.model.Factura;
import com.daiana.saas_subscription_core.model.Plan;
import com.daiana.saas_subscription_core.model.Suscripcion;
import com.daiana.saas_subscription_core.model.Usuario;
import com.daiana.saas_subscription_core.repository.FacturaRepository;
import com.daiana.saas_subscription_core.repository.PlanRepository;
import com.daiana.saas_subscription_core.repository.SuscripcionRepository;
import com.daiana.saas_subscription_core.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;

@Service
public class SuscripcionService {

    private final SuscripcionRepository suscripcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanRepository planRepository;
    private final FacturaRepository facturaRepository;

    public SuscripcionService(SuscripcionRepository suscripcionRepository,
                              UsuarioRepository usuarioRepository,
                              PlanRepository planRepository,
                              FacturaRepository facturaRepository) {
        this.suscripcionRepository = suscripcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.planRepository = planRepository;
        this.facturaRepository = facturaRepository;
    }

    @Transactional
    public Suscripcion activarSuscripcion(SuscripcionRequestDTO dto) {

        // NUEVO: 1. Validar que no tenga otra suscripción activa
        boolean yaTieneActiva = suscripcionRepository.existsByUsuarioIdAndEstado(dto.usuarioId(), "ACTIVA");
        if (yaTieneActiva) {
            throw new RuntimeException("Error: El usuario con ID " + dto.usuarioId() + " ya posee una suscripción ACTIVA. Debe cancelarla antes de contratar una nueva.");
        }

        // 2. Validaciones de existencia de Usuario y Plan
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Error: El usuario no existe en el sistema."));

        Plan plan = planRepository.findById(dto.planId())
                .orElseThrow(() -> new RuntimeException("Error: El plan comercial seleccionado no existe."));

        // 3. Fechas
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaVencimiento = fechaActual.plusDays(plan.getDuracionDias());

        // 4. Crear Suscripción
        Suscripcion nuevaSuscripcion = new Suscripcion();
        nuevaSuscripcion.setUsuario(usuario);
        nuevaSuscripcion.setPlan(plan);
        nuevaSuscripcion.setEstado("ACTIVA");
        nuevaSuscripcion.setFechaInicio(fechaActual);
        nuevaSuscripcion.setFechaVencimiento(fechaVencimiento);

        Suscripcion suscripcionGuardada = suscripcionRepository.save(nuevaSuscripcion);

        // 5. Crear Factura automática
        Factura nuevaFactura = new Factura();
        nuevaFactura.setSuscripcion(suscripcionGuardada);
        nuevaFactura.setMonto(plan.getPrecio());
        nuevaFactura.setEstadoPago("PAGADA");

        facturaRepository.save(nuevaFactura);

        return suscripcionGuardada;
    }

    /**
     * NUEVO: Cancela una suscripción activa cambiando su estado a "CANCELADA".
     */
    @Transactional
    public Suscripcion cancelarSuscripcion(Long suscripcionId) {
        Suscripcion suscripcion = suscripcionRepository.findById(suscripcionId)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró la suscripción con ID " + suscripcionId));

        if ("CANCELADA".equals(suscripcion.getEstado())) {
            throw new RuntimeException("La suscripción con ID " + suscripcionId + " ya se encuentra CANCELADA.");
        }

        suscripcion.setEstado("CANCELADA");
        return suscripcionRepository.save(suscripcion);
    }
}