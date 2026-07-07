package com.daiana.saassubscriptioncore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * unique = true mapea directamente con la restricción UNIQUE que pusimos en SQL.
     * Le prohíbe al sistema registrar dos usuarios con el mismo correo electrónico.
     */
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    /**
     * LocalDateTime es la clase moderna de Java para manejar fechas y horas (TIMESTAMP).
     * updatable = false asegura que una vez que se registra el usuario, la fecha de creación
     * nunca más pueda ser modificada por un error del sistema.
     */
    @Column(name = "fecha_registro", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    // =========================================================================
    // CONSTRUCTORES
    // =========================================================================

    // Constructor vacío obligatorio para Hibernate
    public Usuario() {
    }

    // Constructor completo para uso en nuestro código
    public Usuario(Long id, String nombre, String email, String password, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }

    // =========================================================================
    // GETTERS Y SETTERS (Todo el código de abajo queda ADENTRO de la clase Usuario)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
} // <--- Esta última llave cierra la clase pública Usuario. ¡Todo está adentro!