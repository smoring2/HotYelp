# Code Intelligence Report

## Build System
- **Build tool**: Maven
- **Build files**: `pom.xml`

## Language Versions
- **Java version**: 1.8 (from `<java.version>1.8</java.version>`)

## Spring Boot Version
- **Version**: 2.5.0 (from `spring-boot-starter-parent`)

## Jackson Usage
- **Files with Jackson imports**: 2
  - `src/main/java/rosseneri/model/Business.java` — `com.fasterxml.jackson.annotation.JsonProperty`
  - `src/main/java/rosseneri/model/Review.java` — `com.fasterxml.jackson.annotation.JsonIgnore`, `com.fasterxml.jackson.annotation.JsonProperty`
- **Custom serializers**: 0
- **ObjectMapper usage**: none

## Spring Security
- **Security present**: false
- **SecurityFilterChain count**: 0

## Testing Infrastructure
- **@MockBean count**: 0
- **@SpyBean count**: 0
- **Testcontainers usage**: false
- **Test files**: 0

## Observability
- **Micrometer present**: false
- **OpenTelemetry present**: false
- **Actuator present**: false

## Reactive / WebFlux
- **Reactive files**: 0

## Size Metrics
- **Modules**: 1
- **Java files**: 10
- **Estimated LOC**: 379

## Build Dependencies
  org.springframework.boot:spring-boot-starter-parent
  org.springframework.boot:spring-boot-starter-web
  org.springframework.boot:spring-boot-starter-data-mongodb
  org.springframework.boot:spring-boot-starter-test
  org.springframework.boot:spring-boot-maven-plugin

## Deprecated Boot 3.x APIs
- `javax.*` imports: 0 (no javax.servlet/persistence/validation imports found)
- `WebSecurityConfigurerAdapter`: 0
- `antMatchers`: 0
