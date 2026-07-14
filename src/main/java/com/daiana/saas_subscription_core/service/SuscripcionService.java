package com.daiana.saas_subscription_core.service;

import com.daiana.saas_subscription_core.dto.PlanRequestDTO;
import com.daiana.saas_subscription_core.model.Plan;
import com.daiana.saas_subscription_core.model.Suscripcion;
import com.daiana.saas_subscription_core.model.Usuario;
import com.daiana.saas_subscription_core.repository.PlanRepository;
import com.daiana.saas_subscription_core.repository.SuscripcionRepository;
import com.daiana.saas_subscription_core.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Servicio encargado de la lógica de negocio para la contratación
 * y gestión de los ciclos de vida de las suscripciones.
 */
@Service
public class SuscripcionService {

    // INYECCIÓN DE DEPENDENCIAS: Para dar de alta una suscripción, este cerebro
    // necesita consultar e interactuar con tres tablas distintas de MySQL.
    private final SuscripcionRepository suscripcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanRepository planRepository;

    public SuscripcionService(SuscripcionRepository suscripcionRepository,
                              UsuarioRepository usuarioRepository,
                              PlanRepository planRepository) {
        this.suscripcionRepository = suscripcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.planRepository = planRepository;
    }

    /**
     * MÉTODO DE NEGOCIO: Crear una suscripción para un usuario y un plan específicos.
     * Recibe los IDs sueltos de lo que el cliente quiere unir en la base de datos.
     */
    public Suscripcion activarSuscripcion(Long usuarioId, Long planId) {

        // 1. VALIDACIÓN: Buscar si el usuario realmente existe en MySQL
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Error: El usuario no existe en el sistema."));

        // 2. VALIDACIÓN: Buscar si el plan comercial seleccionado realmente existe
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Error: El plan comercial seleccionado no existe."));

        // 3. LÓGICA DE CALENDARIO: Calcular las fechas del contrato automáticamente
        LocalDate fechaActual = LocalDate.now(); // Captura la fecha de hoy (Año-Mes-Día)
        LocalDate fechaVencimiento = fechaActual.plusDays(30); // Le suma exactamente 30 días corridos

        // 4. CREACIÓN DEL CONTRATO: Instanciamos el objeto del modelo con sus reglas
        Suscripcion nuevaSuscripcion = new Suscripcion();
        nuevaSuscripcion.setUsuario(usuario); // Inyectamos el objeto Usuario completo
        nuevaSuscripcion.setPlan(plan);       // Inyectamos el objeto Plan completo
        nuevaSuscripcion.setEstado("ACTIVA"); // Toda suscripción nueva nace activa
        nuevaSuscripcion.setFechaInicio(fechaActual);
        nuevaSuscripcion.setFechaVencimiento(fechaVencimiento);

        // 5. PERSISTENCIA: Guardamos el contrato definitivo en la tabla "suscripciones" de MySQL
        return suscripcionRepository.save(nuevaSuscripcion);
    }
}