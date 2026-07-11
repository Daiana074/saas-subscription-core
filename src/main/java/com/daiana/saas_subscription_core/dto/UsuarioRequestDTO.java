package com.daiana.saas_subscription_core.dto;

/**
 * Un Record es una estructura de Java ideal para DTOs.
 * Al definir los atributos entre paréntesis, Java automáticamente genera
 * los constructores y métodos de lectura en segundo plano por nosotros.
 * * Este DTO representa los únicos 3 datos obligatorios que el exterior
 * nos tiene que mandar para crear un usuario nuevo.
 */
public record UsuarioRequestDTO(
        String nombre,
        String email,
        String password
) {
    // Los records son limpios, no necesitan variables privadas ni Getters/Setters manuales.
}