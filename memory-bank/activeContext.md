# Active Context

## Current Work Focus
1. Authentication System
   - Implementing JWT authentication
   - Setting up Spring Security
   - Creating user registration and login

2. Core Models
   - User model with JavaFX properties
   - Booking model with JavaFX properties
   - BusSchedule model implementation

3. Repository Layer
   - UserRepository implementation
   - BookingRepository implementation
   - BusScheduleRepository setup

## Recent Changes
1. Authentication
   - Updated JwtUtil to use correct JJWT API
   - Modified AuthenticationService for proper user handling
   - Updated CustomUserDetailsService for Spring Security

2. Models
   - Added JavaFX properties to User model
   - Implemented Booking model with JavaFX properties
   - Created BusSchedule model structure

3. Configuration
   - Updated pom.xml with JavaFX dependencies
   - Modified application.properties for desktop mode
   - Configured Spring Security settings

## Active Decisions
1. Architecture
   - Using JavaFX for desktop UI
   - Spring Boot for backend services
   - H2 database for development

2. Security
   - JWT for authentication
   - BCrypt for password encryption
   - Role-based authorization

3. Data Management
   - JavaFX properties for UI binding
   - JPA for data persistence
   - Repository pattern for data access

## Next Steps
1. Immediate
   - Complete authentication system
   - Implement booking functionality
   - Create basic UI layouts

2. Short-term
   - Add schedule management
   - Implement payment processing
   - Create admin interface

3. Long-term
   - Add offline functionality
   - Implement data synchronization
   - Enhance UI/UX

## Learnings
1. Technical
   - JavaFX property binding
   - Spring Security configuration
   - JWT implementation

2. Architectural
   - Desktop application patterns
   - Security best practices
   - Data synchronization approaches

3. Project Management
   - Dependency management
   - Build configuration
   - Testing strategies 