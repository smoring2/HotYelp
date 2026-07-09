# Code Intelligence Report

## Build System
- Build tool: Maven
- Build file: pom.xml
- Build command: mvn clean verify

## Spring Boot Version
- Version: 2.5.0
- Parent: org.springframework.boot:spring-boot-starter-parent:2.5.0

## Java Version
- java.version: 1.8

## Size Metrics
- Modules: 1
- Estimated LOC: ~500
- Java files: 10
- Kotlin files: 0

## Build Dependencies
  org.springframework.boot:spring-boot-starter-parent
  org.springframework.boot:spring-boot-starter-web
  org.springframework.boot:spring-boot-starter-data-mongodb
  org.springframework.boot:spring-boot-starter-test
  org.springframework.boot:spring-boot-maven-plugin

## Jackson Usage
- Uses com.fasterxml.jackson.annotation.JsonProperty (Business.java, Review.java)
- Uses com.fasterxml.jackson.annotation.JsonIgnore (Review.java)
- Custom serializers: 0

## Spring Security
- Not present

## Testing Infrastructure
- No test classes found
- @MockBean usage: 0
- @SpyBean usage: 0

## Observability
- Not present (no actuator, micrometer, or opentelemetry)

## Reactive / WebFlux
- Not present

## Application Properties
- No application.properties or application.yml files found
