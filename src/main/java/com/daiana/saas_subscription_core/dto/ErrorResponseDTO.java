package com.daiana.saas_subscription_core.dto;

import java.time.LocalDateTime;

/**
 * Estructura estándar para responder los errores de la API de forma limpia.
 */
public record ErrorResponseDTO(
        int status,
        String error,
        String mensaje,
        LocalDateTime timestamp
) {}