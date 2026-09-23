# Code Intelligence Report

## Build System
- Maven (`pom.xml` present, no `build.gradle*`)

## Language Versions
- Java version: 1.8 (from `<java.version>1.8</java.version>`)

## Spring Boot Version
- 2.5.0 (from parent POM `spring-boot-starter-parent`)

## Jackson Usage
- Files with Jackson annotation imports: 2
  - `src/main/java/rosseneri/model/Business.java` — `com.fasterxml.jackson.annotation.JsonProperty`
  - `src/main/java/rosseneri/model/Review.java` — `com.fasterxml.jackson.annotation.JsonIgnore`, `com.fasterxml.jackson.annotation.JsonProperty`
- Custom serializers: 0
- Jackson core/databind imports: 0

## Spring Security
- Security dependencies: none
- SecurityFilterChain methods: 0
- @EnableWebSecurity: 0

## Testing Infrastructure
- @MockBean count: 0
- @SpyBean count: 0
- Testcontainers usage: none
- Test files: 0

## Observability
- Micrometer dependencies: none
- OpenTelemetry dependencies: none
- Actuator dependencies: none

## Reactive / WebFlux
- Reactive files: none

## Size Metrics
- Java files: 10
- Total LOC: 379
- Modules: 1

## Build Dependencies
  org.springframework.boot:spring-boot-starter-parent
  org.springframework.boot:spring-boot-starter-web
  org.springframework.boot:spring-boot-starter-data-mongodb
  org.springframework.boot:spring-boot-starter-test
  org.springframework.boot:spring-boot-maven-plugin

## Deprecated Boot 3.x APIs
- javax.* imports: 0
- com.fasterxml.jackson.core/databind imports: 0
- com.fasterxml.jackson.annotation imports: 2 (UNCHANGED in Jackson 3)
