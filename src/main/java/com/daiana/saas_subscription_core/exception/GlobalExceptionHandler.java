package com.daiana.saas_subscription_core.exception;

import com.daiana.saas_subscription_core.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @RestControllerAdvice intercepta cualquier excepción no controlada
 * que ocurra en nuestros controladores y la transforma en una respuesta JSON clara.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Intercepta las excepciones RuntimeException lanzadas desde nuestros servicios.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> manejarRuntimeException(RuntimeException ex) {

        ErrorResponseDTO errorDTO = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Solicitud Incorrecta / Regla de Negocio",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Intercepta cualquier otra excepción genérica del sistema.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> manejarExcepcionGeneral(Exception ex) {

        ErrorResponseDTO errorDTO = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error Interno del Servidor",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}