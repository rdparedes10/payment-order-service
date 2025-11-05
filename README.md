# 🏦 Payment Order Service – Migración SOAP → REST (Payment Order Initiation)

## 📘 Contexto del Proyecto
La entidad bancaria se encuentra en un proceso de modernización de sus servicios core, migrando gradualmente servicios legados basados en **SOAP** hacia **servicios RESTful**, alineados al estándar **BIAN (Banking Industry Architecture Network)**.

Este proyecto corresponde a la **migración del servicio SOAP de Órdenes de Pago**, el cual forma parte del *Service Domain* **Payment Initiation** (BQ: `PaymentOrder`).  
El objetivo es garantizar:
- **Velocidad** en la migración sin perder calidad.
- **Compatibilidad** con los consumidores actuales.
- **Trazabilidad y calidad** a través de métricas y pruebas.
- **Evidencia de uso de Inteligencia Artificial** como apoyo en el desarrollo.

---

## 🧩 Decisiones de Diseño

| Tema | Decisión                                                                                                                                   |
|------|--------------------------------------------------------------------------------------------------------------------------------------------|
| **Arquitectura** | Se implementó **Arquitectura Hexagonal**, separando dominio, aplicación e infraestructura.                                                 |
| **Contrato REST** | Se diseñó primero el contrato **OpenAPI 3.0 (contract-first)** a partir del análisis del WSDL legado.                                      |
| **Generación de código** | Se utilizó el plugin `openapi-generator-maven-plugin` para generar las interfaces REST.                                                    |
| **Framework** | **Spring Boot 3.3.3 + Java 21**, compatible con WebFlux (reactivo) y R2DBC (PostgreSQL).                                                   |
| **Pruebas** | Unitarias y de integración con **JUnit 5 + Mockito **, cobertura ≥ 80%.                                                                    |
| **Calidad** | Se integraron **Checkstyle** y **SpotBugs**, sin errores (`mvn verify`).                                                                   |
| **Contenedores** | Dockerfile y docker-compose para ejecución orquestada con PostgreSQL en donde se agrega un script  inicial para que cree la tabla inicial. |

---

## ⚙️ Pasos para Ejecución Local

### 🧱 Requisitos previos
- Java 21+
- Maven 3.9+
- PostgreSQL 16+
- Puerto libre: `8080`

### ▶️ Ejecución local

```bash
# Clonar el repositorio
git clone https://github.com/rdparedes10/payment-order-service.git
cd payment-order-service

# Generar clases desde OpenAPI
mvn clean generate-sources

# Compilar y ejecutar
mvn package -DskipTests

# Levantar Docker Compose

docker compose up --build