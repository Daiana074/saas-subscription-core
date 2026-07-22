package com.daiana.saas_subscription_core.controller;

import com.daiana.saas_subscription_core.model.Factura;
import com.daiana.saas_subscription_core.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    /**
     * Obtener todas las facturas (GET http://localhost:8080/api/facturas)
     */
    @GetMapping
    public ResponseEntity<List<Factura>> listarTodas() {
        return ResponseEntity.ok(facturaService.listarTodas());
    }

    /**
     * Obtener facturas por ID de suscripción (GET http://localhost:8080/api/facturas/suscripcion/{id})
     */
    @GetMapping("/suscripcion/{suscripcionId}")
    public ResponseEntity<List<Factura>> listarPorSuscripcion(@PathVariable Long suscripcionId) {
        return ResponseEntity.ok(facturaService.listarPorSuscripcion(suscripcionId));
    }

    /**
     * Actualizar estado de pago de una factura (PUT http://localhost:8080/api/facturas/{id}/estado?nuevoEstado=PAGADA)
     */
    @PutMapping("/{id}/estado")
    public ResponseEntity<Factura> actualizarEstadoPago(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        Factura facturaActualizada = facturaService.actualizarEstadoPago(id, nuevoEstado);
        return ResponseEntity.ok(facturaActualizada);
    }
}