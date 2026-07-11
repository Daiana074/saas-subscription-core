package com.daiana.saas_subscription_core.dto;

import com.daiana.saas_subscription_core.model.Usuario;

/**
 * Este DTO es el que enviaremos de vuelta a la aplicación cliente.
 * Muestra el ID asignado por MySQL, el nombre y el email, pero NUNCA la contraseña.
 */
public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String email
) {
    /**
     * CONSTRUCTOR COMPACTO DE CONVERSIÓN:
     * Creamos este pequeño constructor secundario para que sea facilísimo transformar
     * una entidad completa de la base de datos (Usuario) en este formato seguro.
     */
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}