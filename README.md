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

## 🌐 Acceso y Pruebas de la API

Una vez que el servicio esté en ejecución (ya sea localmente o con Docker), se puede acceder y probar la API mediante **Swagger UI** o **Postman**.

---

### 🧭 Swagger UI (Documentación Interactiva)

✅ **La API estará disponible en:**  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

📄 **Contrato OpenAPI (JSON):**  
[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Desde **Swagger UI** podrás:
- Visualizar la documentación generada automáticamente desde el contrato `openapi/payment-order.yaml`.
- Probar los endpoints directamente desde el navegador.
- Ver ejemplos, parámetros y modelos definidos.

---

### 🧰 Colección de Postman

📂 **Ubicación:**
postman/Hiberus Payment.postman_collection.json

Esta colección incluye las operaciones principales del servicio:

| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| **POST** | `/payment-initiation/payment-orders` | Crea una nueva orden de pago |
| **GET** | `/payment-initiation/payment-orders/{id}` | Recupera una orden existente |
| **GET** | `/payment-initiation/payment-orders/{id}/status` | Obtiene el estado actual de la orden |

#### ▶️ Pasos para usar la colección
1. Abre **Postman**.
2. Haz clic en **Import → File** y selecciona `postman/Payment.postman_collection.json`
3. Ejecuta las peticiones en el siguiente orden:
- `POST` → crear orden de pago
- `GET` → consultar orden
- `GET` → consultar estado

💡 También puedes automatizar las pruebas con **Newman**:
```bash
newman run postman/PaymentOrderCollection.json


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