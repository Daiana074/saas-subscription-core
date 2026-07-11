package com.daiana.saas_subscription_core.controller;

import com.daiana.saas_subscription_core.model.Suscripcion;
import com.daiana.saas_subscription_core.service.SuscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @RestController habilita esta clase como un punto de acceso API en formato JSON.
 * @RequestMapping("/api/suscripciones") mapea la URL base para las transacciones contractuales.
 * Todas las peticiones irán a http://localhost:8080/api/suscripciones
 */
@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {

    // INYECCIÓN DE DEPENDENCIAS: El mozo necesita comunicarse con el chef de suscripciones
    private final SuscripcionService suscripcionService;

    public SuscripcionController(SuscripcionService suscripcionService) {
        this.suscripcionService = suscripcionService;
    }

    /**
     * ENDPOINT: Dar de alta una nueva suscripción en el sistema.
     * @PostMapping define que este endpoint reacciona ante peticiones HTTP POST.
     * @RequestParam le dice a Spring que busque estos datos directamente en las variables de la URL
     * o en el formulario de la petición (por ejemplo: ?usuarioId=1&planId=2).
     */
    @PostMapping
    public ResponseEntity<Suscripcion> crearSuscripcion(
            @RequestParam Long usuarioId,
            @RequestParam Long planId) {

        // El mozo le delega la ejecución de la lógica al servicio correspondiente
        // Cambiá el signo + por un punto (.)
        Suscripcion nuevaSuscripcion = suscripcionService.activarSuscripcion(usuarioId, planId);

        // Retornamos el contrato completo con código HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevaSuscripcion, HttpStatus.CREATED);
    }
}