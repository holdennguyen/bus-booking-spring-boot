# VeXe Bus Booking System

A modern desktop application for bus ticket booking and management, built with Java Spring Boot and JavaFX.

## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
4. [Setup Guide](#setup-guide)
5. [System Architecture](#system-architecture)
6. [Database Design](#database-design)
7. [User Interface](#user-interface)
8. [Code Structure](#code-structure)
9. [Running the Application](#running-the-application)
10. [Troubleshooting](#troubleshooting)
11. [Development Workflow](#development-workflow)
12. [Screenshots](#screenshots)

## Introduction

VeXe is a modern desktop application for bus ticket booking and management. It's designed to provide a seamless experience for both users and administrators in managing bus schedules, routes, and bookings.

### Purpose
- Simplify bus ticket booking process
- Provide real-time schedule management
- Track booking history and analytics
- Offer a modern, user-friendly interface

### Target Users
- Bus passengers
- Bus operators
- System administrators

## Quick Start

1. **Prerequisites**
   - JDK 23
   - Maven 3.8+
   - PostgreSQL 16+
   - NetBeans IDE 19+ (recommended)

2. **Installation**
   ```bash
   # Clone repository
   git clone https://github.com/YOUR_USERNAME/vexe.git
   cd vexe

   # Create database
   createdb vexe

   # Build and run
   mvn clean javafx:run
   ```

## Features

- User authentication and management
- Bus route and schedule management
- Ticket booking system with real-time updates
- Order history tracking and analytics
- Modern Material Design UI with blue theme
- Responsive dashboard with booking statistics
- Database migrations with Flyway

## Setup Guide

### Prerequisites

1. **Java Development Kit (JDK)**
   - Install JDK 23
   - Download from: https://adoptium.net/
   - Set JAVA_HOME environment variable

2. **NetBeans IDE**
   - Download NetBeans 19 or later
   - Download from: https://netbeans.apache.org/
   - Ensure it's configured to use JDK 23

3. **PostgreSQL**
   - Install PostgreSQL 16
   - Download from: https://www.postgresql.org/download/
   - Remember your postgres user password during installation

4. **Maven**
   - NetBeans includes Maven, but you can install it separately
   - Download from: https://maven.apache.org/
   - Minimum version required: 3.8+

### Database Setup
1. Open pgAdmin or your preferred PostgreSQL client
2. Create a new database:
   ```sql
   CREATE DATABASE vexe;
   ```
3. Configure database connection:
   - Username: postgres
   - Password: postgres
   - Database: vexe
   - Port: 5432

   If you want to use different credentials, update them in:
   `src/main/resources/application.properties`

### IDE Setup
1. Open NetBeans
2. Go to File → Open Project
3. Navigate to the cloned vexe directory
4. Select the project and click Open
5. Right-click on the project → Properties
   - Ensure Java platform is set to JDK 23
   - Verify Maven settings are correct

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

### Tables Description

1. **Users Table**
   - Stores user information
   - Fields: id, username, password, email, full_name, phone
   - Used for authentication and user management

2. **Bookings Table**
   - Records all ticket bookings
   - Fields: id, booking_date_time, from_city, to_city, travel_date, travel_time, passenger_count, total_price, bus_type
   - Tracks booking history and information

3. **Buses Table**
   - Manages bus information
   - Fields: id, bus_number, capacity, bus_type
   - Handles bus management

4. **Routes Table**
   - Manages route information
   - Fields: id, origin, destination, distance, duration
   - Handles route management

5. **Bus_Schedules Table**
   - Manages schedule information
   - Relates buses to routes with departure times
   - Fields: id, bus_id, route_id, departure_time, price

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

## Code Structure

### Project Organization
```
src/main/
├── java/com/vexe/
│   ├── config/         # Configuration classes
│   ├── controller/     # Application controllers
│   ├── model/          # Entity classes
│   ├── repository/     # Data access layer
│   ├── service/        # Business logic
│   └── ui/             # JavaFX views
└── resources/
    ├── styles/         # CSS stylesheets
    ├── fxml/           # JavaFX layout files
    └── db/migration/   # Flyway migrations
```

## Running the Application

### Using NetBeans
1. Right-click on the project
2. Select "Clean and Build"
3. Once build is successful, click "Run"
   - Or use: Run → Run Project

### Using Maven Command Line
You can run the application directly from the command line:

```bash
# Using JavaFX Maven Plugin (recommended)
mvn clean javafx:run

# Using Exec Maven Plugin
mvn exec:java
```

The application should start and automatically:
- Create database tables (if not exist)
- Run Flyway migrations
- Load sample data
- Launch the JavaFX interface

## Troubleshooting

### Database Issues
1. If you get connection errors:
   - Verify PostgreSQL is running
   - Check credentials in application.properties
   - Ensure database 'vexe' exists

2. If migrations fail:
   ```bash
   # Connect to database
   psql -U postgres -d vexe

   # Check migration status
   SELECT * FROM flyway_schema_history;

   # If needed, clean and rerun migrations
   mvn flyway:clean flyway:migrate
   ```

### Build Issues
1. Maven dependency problems:
   ```bash
   mvn clean install -U
   ```

2. JavaFX not found:
   - Verify JavaFX dependencies in pom.xml
   - Check Java version compatibility
   - Make sure your JDK has the appropriate modules

### Runtime Issues
1. Port conflicts:
   - Check if PostgreSQL is running on default port 5432
   - Verify no other application is using required ports

2. JavaFX module issues:
   - If running from command line and getting module errors, try using the JavaFX Maven plugin:
   ```bash
   mvn javafx:run
   ```

## Development Workflow
1. Always pull latest changes before starting work:
   ```bash
   git pull origin main
   ```

2. Create feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. Regular commits:
   ```bash
   git add .
   git commit -m "Descriptive message"
   git push origin feature/your-feature-name
   ```

## Screenshots

### Dashboard View
![Dashboard](screenshots/dashboard.png)
*Main dashboard showing booking statistics and recent activities*

### Booking Interface
![Booking](screenshots/booking.png)
*Bus ticket booking interface with schedule selection*

### My Bookings
![My Bookings](screenshots/my-bookings.png)
*User's booking history and management*

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

## Contributing

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 