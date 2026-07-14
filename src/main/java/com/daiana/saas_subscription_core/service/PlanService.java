package com.daiana.saas_subscription_core.service;

import com.daiana.saas_subscription_core.dto.PlanRequestDTO;
import com.daiana.saas_subscription_core.model.Plan;
import com.daiana.saas_subscription_core.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    // Inyección de dependencia por constructor
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    /**
     * Lógica para registrar un nuevo plan de suscripción en el sistema.
     */
    public Plan crearPlan(PlanRequestDTO dto) {
        Plan plan = new Plan();
        plan.setNombre(dto.nombre());
        plan.setDescripcion(dto.descripcion());
        plan.setPrecio(dto.precio());
        plan.setDuracionDias(dto.duracionDias());

        return planRepository.save(plan);
    }

    /**
     * Lógica para listar todos los planes disponibles en el SaaS.
     */
    public List<Plan> listarTodos() {
        return planRepository.findAll();
    }
}