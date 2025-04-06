# VeXe Bus Booking System - Technical Context

## Technology Stack

### Core Technologies
1. Frontend
   - JavaFX 21
   - FXML
   - CSS
   - Material Design

2. Backend
   - Spring Boot 3.2
   - Spring Security
   - Spring Data JPA
   - Spring Web

3. Database
   - PostgreSQL 16
   - Flyway
   - JPA/Hibernate

4. Build & Tools
   - Maven 3.8+
   - JDK 23
   - Git
   - IntelliJ IDEA

## Development Setup

### Prerequisites
1. JDK 23
   ```bash
   brew install openjdk@23
   ```

2. PostgreSQL 16
   ```bash
   brew install postgresql@16
   ```

3. Maven 3.8+
   ```bash
   brew install maven
   ```

### Project Structure
```
src/main/
├── java/com/vexe/
│   ├── config/         # Configuration classes
│   ├── controller/     # Application controllers
│   ├── model/         # Entity classes
│   ├── repository/    # Data access layer
│   ├── service/       # Business logic
│   └── ui/           # JavaFX views
└── resources/
    ├── styles/        # CSS stylesheets
    ├── fxml/         # JavaFX layout files
    └── db/migration/  # Flyway migrations
```

### Dependencies

#### Core Dependencies
```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    
    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.1</version>
    </dependency>
    
    <!-- Flyway -->
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
</dependencies>
```

## Technical Constraints

### Performance Requirements
1. Response Time
   - UI updates: < 100ms
   - Database queries: < 200ms
   - Booking operations: < 1s

2. Scalability
   - Support 1000+ concurrent users
   - Handle 10000+ daily bookings
   - Process 100+ transactions per second

### Security Requirements
1. Authentication
   - Password hashing
   - Session management
   - Token-based auth

2. Data Protection
   - Encrypted storage
   - Secure transmission
   - Access control

## Development Workflow

### Version Control
1. Branch Strategy
   - main: Production code
   - develop: Development branch
   - feature/*: Feature branches
   - hotfix/*: Emergency fixes

2. Commit Guidelines
   - Conventional commits
   - Atomic changes
   - Descriptive messages

### Testing Strategy
1. Unit Tests
   - JUnit 5
   - Mockito
   - Test coverage > 80%

2. Integration Tests
   - Spring Test
   - Database tests
   - API tests

### Deployment Process
1. Build
   ```bash
   mvn clean package
   ```

2. Database Migration
   ```bash
   mvn flyway:migrate
   ```

3. Run Application
   ```bash
   mvn clean javafx:run
   ```

## Tool Usage Patterns

### IDE Configuration
1. IntelliJ IDEA
   - Code style settings
   - Live templates
   - Run configurations

2. Version Control
   - Git integration
   - Branch management
   - Code review tools

### Development Tools
1. Database Tools
   - pgAdmin
   - Flyway CLI
   - Database migrations

2. Testing Tools
   - JUnit
   - Mockito
   - Spring Test 