package com.daiana.saas_subscription_core.service;

import com.daiana.saas_subscription_core.model.Factura;
import com.daiana.saas_subscription_core.model.Suscripcion;
import com.daiana.saas_subscription_core.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * Servicio encargado de la lógica financiera, emisión de comprobantes
 * y control del estado de los pagos de las suscripciones.
 */
@Service
public class FacturaService {

    // INYECCIÓN DE DEPENDENCIAS: Necesitamos el repositorio para impactar las facturas en MySQL.
    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    /**
     * MÉTODO DE NEGOCIO: Emitir una factura automática basada en un contrato de suscripción.
     * @param suscripcion El objeto completo del contrato que se acaba de generar.
     */
    public Factura emitirFacturaInicial(Suscripcion suscripcion) {

        // 1. REGLA DE NEGOCIO: Extraer el precio del plan navegando por los objetos.
        // Gracias a la POO, no necesitamos buscar el precio en la base de datos otra vez;
        // el objeto Suscripcion ya trae adentro su Plan asociado y, por ende, su precio.
        BigDecimal montoAPagar = suscripcion.getPlan().getPrecio();

        // 2. CREACIÓN DEL COMPROBANTE: Instanciamos el modelo de Factura
        Factura nuevaFactura = new Factura();
        nuevaFactura.setSuscripcion(suscripcion); // Amarramos la factura a su contrato
        nuevaFactura.setMonto(montoAPagar);       // Asignamos el monto con precisión matemática (BigDecimal)
        nuevaFactura.setEstadoPago("PENDIENTE");  // Nace pendiente hasta que la pasarela de pago confirme impactación

        // NOTA DE AUDITORÍA: El campo "fechaEmision" no lo seteamos acá porque recordá que pusimos
        // que sea administrado automáticamente por el TIMESTAMP de MySQL.

        // 3. PERSISTENCIA: Guardamos el registro contable en la tabla "facturas" de MySQL
        return facturaRepository.save(nuevaFactura);
    }
}