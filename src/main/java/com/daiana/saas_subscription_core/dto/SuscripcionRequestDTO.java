package com.daiana.saas_subscription_core.dto;

/**
 * Formulario seguro para asociar un usuario con un plan de suscripción.
 */
public record SuscripcionRequestDTO(
        Long usuarioId,
        Long planId
) {}