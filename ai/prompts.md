# 🤖 Prompts utilizados durante el desarrollo

A continuación se listan los principales *prompts* utilizados con IA (ChatGPT) durante la migración del servicio SOAP → REST.

---

### 🟢 Etapa 1 — Análisis y generación del contrato OpenAPI
**Prompt:**
> “Genera un contrato OpenAPI 3.0 para un servicio REST que gestione órdenes de pago alineado al dominio BIAN Payment Initiation (BQ: PaymentOrder), con los endpoints:
> - POST /payment-initiation/payment-orders
> - GET /payment-initiation/payment-orders/{id}
> - GET /payment-initiation/payment-orders/{id}/status  
    > Incluye los esquemas de PaymentOrder, PaymentOrderStatusResponse y PaymentOrderInitiateRequest.”

**Objetivo:**  
Definir el contrato base en formato YAML siguiendo buenas prácticas REST y la especificación OpenAPI 3.0.

---

### 🟢 Etapa 2 — Arquitectura y estructura del microservicio
**Prompt:**
> “Genera la estructura de proyecto en Spring Boot 3 con Java 21 siguiendo arquitectura hexagonal. Incluye capas domain, application e infrastructure y detalla la ubicación del código generado a partir del contrato OpenAPI.”

**Objetivo:**  
Obtener un esquema base para el microservicio y su estructura modular desacoplada.

---

### 🟢 Etapa 3 — Configuración de Docker y docker-compose
**Prompt:**
> “Crea un Dockerfile para un microservicio Spring Boot 3.3 (Java 21) y un docker-compose.yml que levante el servicio junto a una base de datos PostgreSQL 16 con credenciales predeterminadas.”

**Objetivo:**  
Automatizar la ejecución local y asegurar un entorno reproducible.

---

### 🟢 Etapa 4 — Pruebas unitarias y de integración
**Prompt:**
> “Genera pruebas unitarias con JUnit 5 y Mockito para los casos de uso de creación y consulta de órdenes de pago, y una prueba de integración con WebTestClient para verificar los endpoints REST.”

**Objetivo:**  
Asegurar la calidad y cobertura de código mediante pruebas automatizadas.

---
