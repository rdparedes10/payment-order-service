# 🧭 Decisiones tomadas a partir del uso de IA

Este documento describe las decisiones finales adoptadas luego de las sugerencias generadas por IA, junto con las correcciones o ajustes manuales realizados.

---

### 🧱 Diseño del contrato OpenAPI
**IA generó:**  
Estructura inicial de los tres endpoints (`POST`, `GET`, `GET /status`) con modelos base.

**Decisiones:**
- Se ajustaron los nombres de los esquemas (`PaymentOrderRequest`, `PaymentOrderStatusResponse`).
- Se alinearon los campos con la especificación BIAN (id, debtorAccount, creditorAccount, amount, currency, status).
- Se agregaron ejemplos en el contrato para facilitar pruebas automáticas.

---

### 🧩 Arquitectura del proyecto
**IA generó:**  
Una estructura estándar de arquitectura hexagonal con tres capas.

**Decisiones:**
- Se adaptó la infraestructura a **Spring Boot 3 + WebFlux** en lugar de MVC.
- Se reemplazó JPA por **R2DBC** para soporte reactivo.
- Se agregó `openapi-generator-maven-plugin` al `pom.xml` para contract-first.

---

### 🧪 Pruebas unitarias y E2E
**IA generó:**  
Ejemplos de pruebas con `Mockito` y `WebTestClient`.

**Decisiones:**
- Se aumentó la cobertura con pruebas de validación y mapeo.
- Se añadió **JaCoCo** para generar reportes automáticos de cobertura (`≥ 80%`).
- Se ajustaron los mocks de dominio para cumplir con las interfaces de los puertos.

---

### 🐳 Docker y despliegue
**IA generó:**  
Dockerfile básico con `eclipse-temurin:21-jre-alpine` y `docker-compose.yml` con PostgreSQL.

**Decisiones:**
- Se añadió configuración de variables de entorno (`SPRING_R2DBC_URL`, `SPRING_PROFILES_ACTIVE`).
- Se establecieron nombres de contenedor coherentes (`postgres-payment`, `payment-order-service`).

---

### 🧠 Aprendizaje obtenido
El uso de IA permitió acelerar significativamente:
- La generación del contrato REST inicial.
- La estructuración del proyecto bajo buenas prácticas.
- La documentación y automatización del entorno de desarrollo.  
  La intervención humana se centró en **ajustes de negocio, calidad del código y validaciones finales**.
