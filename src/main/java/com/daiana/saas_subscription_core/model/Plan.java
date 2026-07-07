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
     * Le delega el cálculo del ID al motor de la base de datos.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column mapea el atributo con la columna correspondiente en la tabla.
     * nullable = false equivale al NOT NULL que escribimos en el script de base de datos.
     */
    @Column(nullable = false, length = 50)
    private String nombre;

    /**
     * Usamos BigDecimal en Java para mapear el tipo DECIMAL(10,2) de SQL.
     * Es la clase de Java diseñada para cálculos financieros de precisión absoluta.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    // =========================================================================
    // CONSTRUCTORES (Buenas prácticas de POJOs)
    // =========================================================================

    /**
     * Constructor vacío obligatorio.
     * Hibernate lo necesita sí o sí para poder instanciar la clase de forma automática
     * cuando recupera registros de la base de datos.
     */
    public Plan() {
    }

    /**
     * Constructor completo.
     * Nos sirve a nosotros cuando queremos instanciar un objeto Plan en memoria
     * con todos sus datos listos en el código de Java.
     */
    public Plan(Long id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

} // <--- Esta llave cierra formalmente la clase Plan. Todo quedó guardado acá adentro.