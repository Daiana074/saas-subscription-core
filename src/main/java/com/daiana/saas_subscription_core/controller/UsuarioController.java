package com.daiana.saas_subscription_core.controller;

import com.daiana.saas_subscription_core.dto.UsuarioRequestDTO;
import com.daiana.saas_subscription_core.dto.UsuarioResponseDTO;
import com.daiana.saas_subscription_core.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @RestController le avisa a Spring Boot que esta clase va a exponer URLs (endpoints)
 * por internet y que las respuestas automáticas viajarán en formato JSON (el estándar de la industria).
 * * @RequestMapping("/api/usuarios") define la URL base para este controlador.
 * Todas las peticiones que apunten a http://localhost:8080/api/usuarios vendrán acá.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // INYECCIÓN DE DEPENDENCIAS: El mozo necesita comunicarse con el chef de usuarios
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * ENDPOINT: Registrar un usuario nuevo por internet.
     * @PostMapping le dice a Spring que este método solo se activa mediante una petición HTTP POST.
     * @RequestBody le ordena a Spring: "Tomá el JSON que viene navegando por internet,
     * transformalo en un objeto Java y metelo adentro del formulario UsuarioRequestDTO".
     */
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioRequestDTO request) {

        // El mozo le pasa el pedido al chef (servicio) para que procese la regla de negocio
        UsuarioResponseDTO response = usuarioService.registrarUsuario(request);

        // Devolvemos el plato terminado al cliente con un código de estado HTTP 211 (CREATED)
        // Esto le confirma al navegador que el registro en MySQL fue un éxito total.
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}