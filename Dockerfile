# ==========================
# Etapa 1: Build (usando Maven)
# ==========================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia el pom.xml y descarga dependencias primero (para aprovechar la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente
COPY src ./src

# Compila y empaqueta el JAR
RUN mvn clean package -DskipTests

# ==========================
# Etapa 2: Runtime
# ==========================
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copia el JAR compilado desde la etapa anterior
COPY --from=build /app/target/payment-order-service-*.jar app.jar

# Define el puerto del contenedor
EXPOSE 8080

# Configuración por defecto (puedes sobreescribir con -e SPRING_PROFILES_ACTIVE=prod)
ENV SPRING_PROFILES_ACTIVE=default

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]