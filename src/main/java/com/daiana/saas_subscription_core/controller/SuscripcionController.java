package com.daiana.saas_subscription_core.controller;

import com.daiana.saas_subscription_core.dto.SuscripcionRequestDTO;
import com.daiana.saas_subscription_core.model.Suscripcion;
import com.daiana.saas_subscription_core.service.SuscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {

    private final SuscripcionService suscripcionService;

    public SuscripcionController(SuscripcionService suscripcionService) {
        this.suscripcionService = suscripcionService;
    }

    @PostMapping
    public ResponseEntity<Suscripcion> crearSuscripcion(@RequestBody SuscripcionRequestDTO dto) {
        Suscripcion nuevaSuscripcion = suscripcionService.activarSuscripcion(dto);
        return new ResponseEntity<>(nuevaSuscripcion, HttpStatus.CREATED);
    }

    /**
     * Endpoint para cancelar una suscripción activa (PUT http://localhost:8080/api/suscripciones/{id}/cancelar)
     */
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Suscripcion> cancelarSuscripcion(@PathVariable Long id) {
        Suscripcion suscripcionCancelada = suscripcionService.cancelarSuscripcion(id);
        return ResponseEntity.ok(suscripcionCancelada);
    }
}