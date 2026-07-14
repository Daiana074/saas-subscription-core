package com.daiana.saas_subscription_core.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * La anotación @Entity le dice a Hibernate que esta clase representa
 * una entidad que se va a mapear con una tabla de la base de datos.
 */
@Entity
/**
 * @Table especifica el nombre exacto de la tabla física en MySQL.
 * Al usar "planes", vinculamos este objeto con nuestro catálogo de precios.
 */
@Table(name = "planes")
public class Plan {

    /**
     * @Id define que este atributo es la Clave Primaria (Primary Key) de la tabla.
     */
    @Id
    /**
     * @GeneratedValue especifica la estrategia de generación de la clave.
     * GenerationType.IDENTITY mapea directamente con el AUTO_INCREMENT de MySQL.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column mapea el atributo con la columna correspondiente en la tabla.
     * nullable = false equivale al NOT NULL.
     */
    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    /**
     * Usamos BigDecimal en Java para mapear el tipo DECIMAL(10,2) de SQL.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "duracion_dias", nullable = false)
    private Integer duracionDias;

    // =========================================================================
    // CONSTRUCTORES (Buenas prácticas de POJOs)
    // =========================================================================

    /**
     * Constructor vacío obligatorio para Hibernate.
     */
    public Plan() {
    }

    /**
     * Constructor completo para instanciarlo con todos sus campos de forma manual.
     */
    public Plan(Long id, String nombre, String descripcion, BigDecimal precio, Integer duracionDias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionDias = duracionDias;
    }

    // =========================================================================
    // GETTERS Y SETTERS (Concepto de Encapsulamiento)
    // =========================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }

} // <--- Esta llave cierra formalmente la clase Plan.