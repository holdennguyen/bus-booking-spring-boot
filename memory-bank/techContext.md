# Technical Context

## Technologies
1. Core Technologies
   - Java 17
   - JavaFX 17
   - Spring Boot 3.x
   - H2 Database
   - Maven

2. Security
   - Spring Security
   - JWT (jjwt)
   - BCrypt

3. UI
   - JavaFX
   - FXML
   - CSS

4. Testing
   - JUnit 5
   - Mockito
   - Spring Test

## Development Setup
1. Environment
   - JDK 17
   - Maven 3.8+
   - IDE with JavaFX support

2. Dependencies
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
           <version>17</version>
       </dependency>
       
       <!-- Database -->
       <dependency>
           <groupId>com.h2database</groupId>
           <artifactId>h2</artifactId>
           <scope>runtime</scope>
       </dependency>
       
       <!-- Security -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-security</artifactId>
       </dependency>
       <dependency>
           <groupId>io.jsonwebtoken</groupId>
           <artifactId>jjwt-api</artifactId>
           <version>0.11.5</version>
       </dependency>
   </dependencies>
   ```

3. Configuration
   - application.properties
   - JavaFX plugin configuration
   - Security configuration

## Technical Constraints
1. Platform
   - Desktop-only application
   - JavaFX compatibility
   - H2 database limitations

2. Performance
   - Memory usage
   - Database performance
   - UI responsiveness

3. Security
   - JWT token expiration
   - Password encryption
   - Role-based access

## Tool Usage
1. Development
   - Maven for build
   - JavaFX Scene Builder
   - Spring Boot DevTools

2. Testing
   - JUnit for unit tests
   - Mockito for mocking
   - Spring Test for integration

3. Version Control
   - Git
   - Maven versioning
   - Dependency management 