# System Patterns

## Architecture
1. Desktop Application Architecture
   - JavaFX for UI layer
   - Spring Boot for backend services
   - H2 database for data storage
   - RESTful API for communication

2. Component Relationships
   ```
   UI Layer (JavaFX)
      ↓
   Controller Layer
      ↓
   Service Layer
      ↓
   Repository Layer
      ↓
   Database (H2)
   ```

## Design Patterns
1. MVC Pattern
   - Model: Entity classes (User, Booking, BusSchedule)
   - View: JavaFX FXML files
   - Controller: JavaFX controllers and REST controllers

2. Repository Pattern
   - JpaRepository interfaces
   - Custom query methods
   - Data access abstraction

3. Service Layer Pattern
   - Business logic encapsulation
   - Transaction management
   - Service interfaces

4. Factory Pattern
   - Object creation abstraction
   - Dependency injection
   - Configuration management

## Critical Implementation Paths
1. Authentication Flow
   ```
   User Input → AuthenticationController → AuthenticationService → JwtUtil → Token
   ```

2. Booking Flow
   ```
   User Input → BookingController → BookingService → BusScheduleService → Database
   ```

3. Schedule Management
   ```
   Admin Input → BusScheduleController → BusScheduleService → Database
   ```

## Security Patterns
1. JWT Authentication
   - Token generation
   - Token validation
   - Role-based access

2. Password Security
   - BCrypt encryption
   - Secure storage
   - Password validation

3. Authorization
   - Role-based access control
   - Method-level security
   - Resource protection 