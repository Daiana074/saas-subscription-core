# 🚀 SaaS Subscription & Billing Core

Un sistema backend robusto, centralizado y transaccional para la gestión de usuarios, planes comerciales, contratos de suscripción y facturación automatizada (SaaS Core). Diseñado y desarrollado bajo arquitectura limpia y buenas prácticas de desarrollo en **Java** y **Spring Boot**.

---

## 👩‍💻 Desarrollado por
* **Nombre:** Daiana Camacho
* **GitHub:** [@Daiana074](https://github.com/Daiana074)
* **Contacto:** daii.camacho31@gmail.com

---

## 🎯 Objetivos y Características Principales

* **Gestión de Usuarios y Planes:** Registro de clientes y configuración de planes comerciales con reglas de negocio dinámicas (precio y duración en días).
* **Motor de Suscripciones Transaccional:** Procesamiento de altas de suscripciones con cálculo automático de fechas de vencimiento según el plan seleccionado.
* **Control de Reglas de Negocio:** Validación estricta para evitar suscripciones activas duplicadas por usuario y habilitación de gestión de ciclo de vida (Cancelaciones).
* **Facturación Automatizada:** Emisión instantánea e íntegra de facturas al confirmar una contratación, utilizando relaciones relacionales y precisión en BigDecimal.
* **Mapeo DTO y Manejo de Excepciones:** Uso de Records/DTOs para encapsulamiento y un `@RestControllerAdvice` global que responde con estándares HTTP limpios (`400 Bad Request`, `404 Not Found`).

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17+
* **Framework Backend:** Spring Boot 3 (Spring Data JPA, Spring Web)
* **Base de Datos:** MySQL Server (Persistencia Relacional)
* **Herramientas de Desarrollo & Pruebas:** Maven, Git, GitHub, Postman, IntelliJ IDEA

---

## 📌 Endpoints de la API REST

### 👤 Usuarios (`/api/usuarios`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/usuarios` | Registrar un nuevo usuario |
| `GET` | `/api/usuarios` | Listar todos los usuarios |
| `GET` | `/api/usuarios/{id}` | Buscar usuario por ID |

### 💼 Planes Comerciales (`/api/planes`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/planes` | Crear un nuevo plan comercial |
| `GET` | `/api/planes` | Listar planes disponibles |
| `GET` | `/api/planes/{id}` | Buscar plan por ID |

### 💳 Suscripciones (`/api/suscripciones`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/suscripciones` | Activar suscripción (genera factura automática y valida no duplicados) |
| `GET` | `/api/suscripciones` | Listar todas las suscripciones |
| `GET` | `/api/suscripciones/usuario/{id}` | Consultar historial de suscripciones de un usuario |
| `PUT` | `/api/suscripciones/{id}/cancelar` | Cancelar una suscripción activa |

### 📄 Facturación (`/api/facturas`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/facturas` | Listar todas las facturas del sistema |
| `GET` | `/api/facturas/suscripcion/{id}` | Consultar facturas de una suscripción |
| `PUT` | `/api/facturas/{id}/estado` | Actualizar estado de pago (`PAGADA`, `PENDIENTE`, `ANULADA`) |

---

## 📅 Estado del Proyecto: 🟢 Backend Core Completado (100%)

- [x] **Fase 1:** Diseño del Modelo Entidad-Relación y Tablas Relacionales en MySQL.
- [x] **Fase 2:** Construcción del Backend Core API REST con Spring Boot, DTOs y Servicios.
- [x] **Fase 3:** Lógica de Negocio Transaccional (Cálculo de fechas, Facturación Automática, Manejo Global de Excepciones).
- [x] **Fase 4:** Pruebas e Integración de Endpoints mediante Postman.
- [ ] **Fase 5:** Integración de Servicios Adicionales / Despliegue en la Nube.

---

## ⚙️ Cómo ejecutar el proyecto localmente

1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/Daiana074/saas-subscription-core.git](https://github.com/Daiana074/saas-subscription-core.git)