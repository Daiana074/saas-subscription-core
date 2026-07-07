package com.daiana.saas_subscription_core.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * La anotación @Entity mapea esta clase con Hibernate.
 * @Table la vincula con la tabla física "suscripciones" en MySQL.
 */
@Entity
@Table(name = "suscripciones")
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * RELACIÓN: Muchas suscripciones pertenecen a un único Usuario.
     * @ManyToOne representa la clave foránea (FK) de la base de datos.
     * @JoinColumn especifica el nombre de la columna FK física en MySQL (usuario_id)
     * y asegura que sea obligatoria (nullable = false).
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /**
     * RELACIÓN: Muchas suscripciones se basan en un único Plan.
     * Aquí se ve el poder de la POO: en vez de un "Long planId", inyectamos el objeto Plan completo.
     */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(nullable = false, length = 20)
    private String estado;

    /**
     * Usamos LocalDate (de java.time) porque en MySQL definimos estos campos como DATE.
     * Almacena solo la fecha (año, mes, día) sin la hora, ideal para vencimientos.
     */
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    // =========================================================================
    // CONSTRUCTORES (Buenas prácticas de encapsulamiento)
    // =========================================================================

    // Constructor vacío requerido por Hibernate
    public Suscripcion() {
    }

    // Constructor completo para instanciar en la lógica de negocio
    public Suscripcion(Long id, Usuario usuario, Plan plan, String estado, LocalDate fechaInicio, LocalDate fechaVencimiento) {
        this.id = id;
        this.usuario = usuario;
        this.plan = plan;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
    }

    // =========================================================================
    // GETTERS Y SETTERS (Encapsulamiento estricto POO)
    // =========================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Retorna el objeto Usuario completo, permitiendo navegar por sus datos
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Retorna el objeto Plan completo
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

} // <--- Cierre de la clase Suscripcion