package com.daiana.saas_subscription_core.service;

import com.daiana.saas_subscription_core.dto.UsuarioRequestDTO;
import com.daiana.saas_subscription_core.dto.UsuarioResponseDTO;
import com.daiana.saas_subscription_core.model.Usuario;
import com.daiana.saas_subscription_core.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * La anotación @Service le avisa a Spring Boot que esta clase contiene
 * la lógica de negocio principal del sistema y debe ser registrada como un componente.
 */
@Service
public class UsuarioService {

    /**
     * INYECCIÓN DE DEPENDENCIAS: El servicio necesita del repositorio para
     * poder guardar los datos en MySQL. Declaramos la variable como "final"
     * por seguridad (para que no pueda ser alterada en ejecución).
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor para que Spring Boot inyecte automáticamente el repositorio
     * correspondiente cuando la aplicación arranque.
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * MÉTODO DE NEGOCIO: Registrar un nuevo usuario de forma segura.
     * Recibe el "formulario" (RequestDTO) y devuelve la "respuesta protegida" (ResponseDTO).
     */
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request) {

        // 1. REGLA DE NEGOCIO: Validar que el email no esté registrado en la base de datos
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            // Si el método mágico del repositorio encuentra el mail, frena el sistema con una excepción
            throw new RuntimeException("El correo electrónico ya se encuentra registrado.");
        }

        // 2. CREACIÓN DEL OBJETO: Instanciamos un usuario real de la capa 'model'
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.nombre());
        nuevoUsuario.setEmail(request.email());

        // NOTA DE PRODUCCIÓN: Acá es donde más adelante aplicaremos el encriptador de contraseñas.
        // Por ahora, para armar la estructura base, guardamos el texto plano.
        nuevoUsuario.setPassword(request.password());

        // 3. PERSISTENCIA: Le ordenamos al repositorio que ejecute el INSERT en MySQL
        // El método .save() nos devuelve el usuario ya guardado, con el ID asignado por el AUTO_INCREMENT.
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // 4. TRANSFORMACIÓN SEGURA: Convertimos el modelo a un formato seguro y lo devolvemos
        return new UsuarioResponseDTO(usuarioGuardado);
    }
}