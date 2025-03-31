# Technical Context

## Technology Stack
- **Backend Framework**: Java Spring Boot
- **Frontend**: JavaFX for desktop UI
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Java Version**: 17 LTS
- **Testing**: JUnit 5, Mockito

## Development Environment
### Prerequisites
- JDK 17
- Maven 3.8+
- PostgreSQL 15+
- IDE (IntelliJ IDEA recommended)

### Database Configuration
- Database Name: vexe_db
- Port: 5432
- Local development setup

## Dependencies
### Core Dependencies
- Spring Boot Starter
- Spring Data JPA
- JavaFX
- PostgreSQL Driver
- Lombok
- Spring Security
- JUnit and Mockito for testing

### Development Dependencies
- Spring Boot DevTools
- Spring Boot Test

## Build & Deployment
### Build Process
```bash
mvn clean install
```

### Run Application
```bash
mvn spring-boot:run
```

## Testing Framework
- JUnit 5 for unit testing
- Mockito for mocking
- TestContainers for database testing
- Integration tests for critical flows

## Development Tools
- IntelliJ IDEA (recommended IDE)
- DBeaver/pgAdmin for database management
- Git for version control
- Maven for dependency management

## Notes
- Local development setup prioritized for prototype
- Database migrations will be handled through Flyway
- Sample data scripts will be provided
- JavaFX used for desktop UI implementation
- This document will be updated as we make technical decisions
- All development setup instructions should be documented here
- Tool versions and configurations should be specified 