# API REST Sincrono Java

A synchronous REST API built with Java and Spring Boot.

## Architecture

- **Language**: Java 19 (GraalVM 22.3)
- **Framework**: Spring Boot 3.2.3
- **Build tool**: Maven 3.8.6
- **Port**: 5000

## Project Structure

```
src/
  main/
    java/com/example/api/
      ApiRestSincronoApplication.java  - Spring Boot entry point
      controller/
        HealthController.java          - Root health endpoint
        ProductController.java         - CRUD REST endpoints for products
      model/
        Product.java                   - Product entity
      service/
        ProductService.java            - In-memory product service
    resources/
      application.properties           - Server config (port 5000, host 0.0.0.0)
pom.xml                                - Maven dependencies
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | / | Health check |
| GET | /api/products | List all products |
| GET | /api/products/{id} | Get product by ID |
| POST | /api/products | Create product |
| PUT | /api/products/{id} | Update product |
| DELETE | /api/products/{id} | Delete product |

## Running

```bash
mvn spring-boot:run
```

Or build and run the JAR:

```bash
mvn package -DskipTests
java -jar target/api-rest-sincrono-0.0.1-SNAPSHOT.jar
```

## Notes

- Data is stored in-memory (resets on restart)
- CORS is enabled for all origins
- Spring Actuator exposes `/actuator/health` and `/actuator/info`
