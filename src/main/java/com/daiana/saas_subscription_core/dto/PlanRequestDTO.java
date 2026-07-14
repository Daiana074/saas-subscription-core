package com.daiana.saas_subscription_core.dto;

import java.math.BigDecimal;

/**
 * Formulario seguro para recibir los datos de un nuevo plan desde internet.
 */
public record PlanRequestDTO(
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer duracionDias
) {}