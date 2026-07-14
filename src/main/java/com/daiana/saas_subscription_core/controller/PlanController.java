package com.daiana.saas_subscription_core.controller;

import com.daiana.saas_subscription_core.dto.PlanRequestDTO;
import com.daiana.saas_subscription_core.model.Plan;
import com.daiana.saas_subscription_core.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    /**
     * Endpoint para registrar un plan (POST http://localhost:8080/api/planes)
     */
    @PostMapping
    public ResponseEntity<Plan> crear(@RequestBody PlanRequestDTO dto) {
        Plan nuevoPlan = planService.crearPlan(dto);
        return new ResponseEntity<>(nuevoPlan, HttpStatus.CREATED);
    }

    /**
     * Endpoint para ver todos los planes (GET http://localhost:8080/api/planes)
     */
    @GetMapping
    public ResponseEntity<List<Plan>> listar() {
        List<Plan> planes = planService.listarTodos();
        return ResponseEntity.ok(planes);
    }
}