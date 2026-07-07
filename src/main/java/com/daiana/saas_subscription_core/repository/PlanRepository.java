package com.daiana.saas_subscription_core.repository;

import com.daiana.saas_subscription_core.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * La anotación @Repository le avisa a Spring Boot que esta interfaz
 * es un componente de acceso a datos y debe ser administrado por su contenedor.
 * * Al extender (implementar) JpaRepository<Plan, Long>, le estamos diciendo a Spring:
 * 1. "Quiero que manejes la entidad Plan".
 * 2. "El tipo de dato de su Clave Primaria (ID) es un Long".
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    // Aunque acá adentro no escribamos NADA de código, por herencia esta interfaz
    // ya tiene mágicamente disponibles los métodos:
    // - save(Plan plan) -> Para insertar o actualizar un plan.
    // - findById(Long id) -> Para buscar un plan por su ID.
    // - findAll() -> Para traer la lista de todos los planes (el SELECT * FROM planes).
    // - deleteById(Long id) -> Para borrar un registro.
}