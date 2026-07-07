package com.daiana.saas_subscription_core.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * La anotación @Entity define que esta clase es una entidad JPA.
 * @Table la vincula con la tabla transaccional "facturas" en MySQL.
 */
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * RELACIÓN: Muchas facturas pueden pertenecer a una sola Suscripción.
     * @ManyToOne representa la clave foránea en la base de datos.
     * @JoinColumn vincula este atributo con la columna física "suscripcion_id".
     */
    @ManyToOne
    @JoinColumn(name = "suscripcion_id", nullable = false)
    private Suscripcion suscripcion;

    /**
     * Usamos BigDecimal para garantizar la precisión decimal del dinero en el monto.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    /**
     * LocalDateTime maneja la fecha y la hora exacta de emisión (TIMESTAMP).
     */
    @Column(name = "fecha_emision", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaEmision;

    @Column(name = "estado_pago", nullable = false, length = 20)
    private String estadoPago;

    // =========================================================================
    // CONSTRUCTORES
    // =========================================================================

    // Constructor vacío obligatorio para Hibernate
    public Factura() {
    }

    // Constructor completo para usar en nuestra lógica
    public Factura(Long id, Suscripcion suscripcion, BigDecimal monto, LocalDateTime fechaEmision, String estadoPago) {
        this.id = id;
        this.suscripcion = suscripcion;
        this.monto = monto;
        this.fechaEmision = fechaEmision;
        this.estadoPago = estadoPago;
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

    // Retorna el objeto Suscripcion completo, permitiendo auditar el contrato asociado
    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

} // <--- Cierre de la clase Factura