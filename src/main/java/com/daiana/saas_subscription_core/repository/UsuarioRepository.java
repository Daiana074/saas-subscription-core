package com.daiana.saas_subscription_core.repository;

import com.daiana.saas_subscription_core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio encargado de gestionar las operaciones de datos de la entidad Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * MÉTODO PERSONALIZADO (Query Method):
     * Spring Data JPA es tan inteligente que si declaramos un método con esta estructura de nombres,
     * interpreta automáticamente que queremos hacer un: "SELECT * FROM usuarios WHERE email = ?".
     * * Usamos Optional<Usuario> como una buena práctica de POO para evitar el famoso error NullPointerException.
     * Si el email existe, el Optional vendrá lleno; si no existe, vendrá vacío de forma segura.
     */
    Optional<Usuario> findByEmail(String email);
}