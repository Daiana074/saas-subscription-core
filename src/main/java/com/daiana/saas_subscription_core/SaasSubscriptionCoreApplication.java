package com.daiana.saas_subscription_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Esta anotación @SpringBootApplication es la magia pura del framework.
 * Hace tres cosas vitales al mismo tiempo:
 * 1. Activa la autoconfiguración de Spring (lee tu application.properties).
 * 2. Enciende el escáner de componentes (busca automáticamente tus carpetas
 * @Entity, @Repository, @Service y @RestController para coordinarlos).
 * 3. Levanta un servidor web interno (Tomcat) en el puerto 8080.
 */
@SpringBootApplication
public class SaasSubscriptionCoreApplication {

    /**
     * El método main es el punto de inicio de cualquier programa en Java.
     * Al ejecutar esta clase, se enciende todo el ecosistema del backend.
     */
    public static void main(String[] args) {
        SpringApplication.run(SaasSubscriptionCoreApplication.class, args);
    }
}