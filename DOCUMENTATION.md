# VeXe Bus Booking System - Detailed Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [System Architecture](#system-architecture)
3. [Database Design](#database-design)
4. [User Interface](#user-interface)
5. [Features and Functionality](#features-and-functionality)
6. [Development Setup](#development-setup)
7. [Code Structure](#code-structure)
8. [Testing and Deployment](#testing-and-deployment)
9. [Troubleshooting](#troubleshooting)

## Introduction

VeXe is a modern desktop application for bus ticket booking and management. It's designed to provide a seamless experience for both users and administrators in managing bus schedules and bookings.

### Purpose
- Simplify bus ticket booking process
- Provide real-time schedule management
- Track booking history and analytics
- Offer a modern, user-friendly interface

### Target Users
- Bus passengers
- Bus operators
- System administrators

## System Architecture

### Technology Stack
- **Frontend**: JavaFX (Desktop UI)
- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Database Migration**: Flyway

### Architecture Overview
```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│                 │     │                 │     │                 │
│  JavaFX UI      │◄───►│  Spring Boot    │◄───►│  PostgreSQL    │
│  (Frontend)     │     │  (Backend)      │     │  (Database)    │
│                 │     │                 │     │                 │
└─────────────────┘     └─────────────────┘     └─────────────────┘
```

## Database Design

### Entity Relationship Diagram
```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│   Users     │     │  Bookings   │     │ BusSchedules│
└─────────────┘     └─────────────┘     └─────────────┘
      ▲                   ▲                   ▲
      │                   │                   │
      └───────────────────┴───────────────────┘
```

### Tables Description

1. **Users Table**
   - Stores user information
   - Fields: id, username, password, email, full_name, phone
   - Used for authentication and user management

2. **Bookings Table**
   - Records all ticket bookings
   - Fields: id, user_id, schedule_id, booking_date, seat_number, status, total_price
   - Tracks booking history and current status

3. **BusSchedules Table**
   - Manages bus schedules and routes
   - Fields: id, route_id, bus_id, departure_time, arrival_time, price, available_seats
   - Handles schedule management and availability

## User Interface

### Main Components

1. **Dashboard**
   - Overview of booking statistics
   - Recent activities
   - Quick access to main features

2. **Booking Interface**
   - Route selection
   - Schedule browsing
   - Seat selection
   - Payment processing

3. **My Bookings**
   - Booking history
   - Ticket management
   - Cancellation options

### UI Theme
- Primary Color: #1976D2 (Material Blue)
- Secondary Color: #1565C0 (Darker Blue)
- Accent Color: #2196F3 (Light Blue)
- Background: #F5F5F5 (Light Gray)
- Text: #2C3E50 (Dark Gray)

## Features and Functionality

### 1. User Management
- User registration and login
- Profile management
- Password reset functionality

### 2. Booking System
- Real-time schedule viewing
- Seat selection
- Multiple passenger booking
- Price calculation
- Booking confirmation

### 3. Schedule Management
- Route management
- Schedule updates
- Availability tracking
- Price management

### 4. Analytics
- Booking statistics
- Revenue tracking
- Popular routes
- Peak hours analysis

## Development Setup

### Prerequisites
- JDK 23 or later
- Maven 3.8+
- PostgreSQL 16+
- IDE (IntelliJ IDEA recommended)

### Installation Steps

1. **Environment Setup**
   ```bash
   # Install JDK
   brew install openjdk@23

   # Install PostgreSQL
   brew install postgresql@16

   # Install Maven
   brew install maven
   ```

2. **Database Setup**
   ```bash
   # Create database
   createdb vexe

   # Configure connection
   # Edit src/main/resources/application.properties
   ```

3. **Project Setup**
   ```bash
   # Clone repository
   git clone https://github.com/holdennguyen/bus-booking-spring-boot.git
   cd bus-booking-spring-boot

   # Build project
   mvn clean install

   # Run application
   mvn clean javafx:run
   ```

## Code Structure

### Project Organization
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

### Key Components

1. **Controllers**
   - `MainController`: Main application controller
   - `BookingController`: Handles booking operations
   - `DashboardController`: Manages dashboard view
   - `MyBookingsController`: Handles booking history

2. **Models**
   - `User`: User entity
   - `Booking`: Booking entity
   - `BusSchedule`: Schedule entity

3. **Services**
   - `BookingService`: Booking business logic
   - `UserService`: User management
   - `BusScheduleService`: Schedule management

## Testing and Deployment

### Testing
1. **Unit Testing**
   ```bash
   mvn test
   ```

2. **Integration Testing**
   - Database integration tests
   - UI component tests

### Deployment
1. **Build Process**
   ```bash
   mvn clean package
   ```

2. **Database Migration**
   ```bash
   mvn flyway:migrate
   ```

## Troubleshooting

### Common Issues

1. **Database Connection**
   ```bash
   # Check PostgreSQL status
   pg_isready

   # Verify database exists
   psql -l | grep vexe
   ```

2. **Build Issues**
   ```bash
   # Clear Maven cache
   mvn clean

   # Update dependencies
   mvn dependency:resolve
   ```

3. **Runtime Issues**
   - Check Java version: `java -version`
   - Verify database credentials
   - Check application logs

### Debugging Tips
1. Enable debug logging in `application.properties`
2. Check PostgreSQL logs
3. Use IDE debugger for JavaFX issues

## Additional Resources

1. **Documentation**
   - [JavaFX Documentation](https://openjfx.io/)
   - [Spring Boot Documentation](https://spring.io/projects/spring-boot)
   - [PostgreSQL Documentation](https://www.postgresql.org/docs/)

2. **Tutorials**
   - JavaFX UI Development
   - Spring Boot Backend Development
   - PostgreSQL Database Management

3. **Support**
   - GitHub Issues
   - Stack Overflow
   - Project Wiki 