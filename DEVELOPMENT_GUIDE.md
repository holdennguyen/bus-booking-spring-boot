# VeXe Bus Booking System - Development Guide for Beginners

This guide explains the development process of the VeXe Bus Booking System, a JavaFX application built with Spring Boot and PostgreSQL. It's designed to help beginners understand how a real-world Java application is structured and developed.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Technology Stack](#technology-stack)
3. [Development Phases](#development-phases)
4. [Application Architecture](#application-architecture)
5. [Key Components](#key-components)
6. [Database Evolution](#database-evolution)
7. [Learning Path](#learning-path)
8. [Advanced Topics](#advanced-topics)

## Project Overview

VeXe is a bus booking management system that allows users to:
- Browse bus schedules between cities
- View bus details including amenities
- Book tickets for specific routes
- Track booking history
- View booking statistics

The application demonstrates core concepts in Java development, Spring Framework, database design, and user interface development with JavaFX.

## Technology Stack

The application uses the following technologies:

- **Backend**:
  - Java 23 - Programming language
  - Spring Boot 3.2.3 - Application framework
  - Spring Data JPA - Data access layer
  - Hibernate - ORM (Object-Relational Mapping)
  - Flyway - Database migration

- **Frontend**:
  - JavaFX - UI framework for desktop applications
  - FXML - XML-based UI markup language
  - CSS - Styling

- **Database**:
  - PostgreSQL 16 - Relational database

- **Build Tools**:
  - Maven - Dependency management and build automation

## Development Phases

The application was developed in several phases, which represent a realistic development process:

### 1. Project Setup and Planning
- Created project structure using Spring Boot
- Set up Maven dependencies
- Designed database schema
- Identified key features and components

### 2. Database Design and Evolution
- Created initial database schema
- Used Flyway for database migrations
- Started with a complex schema including users and schedules
- Simplified the design as development progressed
- Populated with sample data

### 3. Core Data Models and Repositories
- Developed entity classes (models)
- Created JPA repositories for data access
- Implemented services for business logic

### 4. UI Development with JavaFX
- Designed FXML layouts for different views
- Created controllers for UI logic
- Implemented navigation between screens
- Styled the application with CSS

### 5. Feature Implementation
- Created booking functionality
- Implemented schedule search and filtering
- Added statistics dashboard
- Developed booking history view

### 6. Testing and Refinement
- Manual testing of features
- Bug fixes and improvements
- Performance optimization

## Application Architecture

The application follows a layered architecture common in Spring applications:

```
┌─────────────────┐
│   UI Layer      │   FXML files, Controllers, CSS
└────────┬────────┘
         │
┌────────▼────────┐
│  Service Layer  │   Business logic, data processing
└────────┬────────┘
         │
┌────────▼────────┐
│ Repository Layer│   Data access objects
└────────┬────────┘
         │
┌────────▼────────┐
│   Model Layer   │   Entity classes (JPA)
└────────┬────────┘
         │
┌────────▼────────┐
│     Database    │   PostgreSQL tables managed by Flyway
└─────────────────┘
```

This separation of concerns is a fundamental principle in software design and makes the application more maintainable and testable.

## Key Components

### 1. Entity Classes (Models)

Entity classes are Java classes that represent data stored in the database. They use modern JPA patterns and clean code practices.

**Example: BusSchedule.java**
```java
@Data
@NoArgsConstructor
@Entity
@Table(name = "bus_schedules")
public class BusSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String time;
    private String fromCity;
    private String toCity;
    private String busType;
    private int availableSeats;
    private double price;
    private int estimatedDuration;
    
    @ElementCollection
    @CollectionTable(name = "bus_schedule_amenities", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "amenity")
    private List<String> amenities;
    
    public boolean hasAmenity(String amenity) {
        return amenities != null && amenities.contains(amenity);
    }
}
```

Key points:
- Removed redundant annotations (e.g., `@AllArgsConstructor` when custom constructor exists)
- Simplified amenities handling with `@ElementCollection`
- Added helper methods for common operations
- Clean and focused design

### Best Practices for Entity Classes

1. **Property Management**
   - Use `@ElementCollection` for simple one-to-many relationships
   - Implement helper methods for common checks
   - Keep constructors focused and minimal
   - Use appropriate data types (e.g., BigDecimal for currency)

2. **JavaFX Integration**
   - Initialize properties in constructors
   - Use `@PostLoad` for automatic updates
   - Keep UI-specific logic in controllers
   - Handle property bindings efficiently

3. **Data Validation**
   - Use JPA annotations for constraints
   - Implement business rules in service layer
   - Keep validation logic consistent
   - Provide clear error messages

### Controller Design

Controllers should follow these principles:

1. **Clean Separation of Concerns**
   ```java
   @Controller
   public class BookingController {
       private final BookingService bookingService;
       private final BusScheduleService busScheduleService;
       
       @FXML
       private void handleSearch() {
           // Validate input
           if (!validateSearchCriteria()) {
               showAlert(Alert.AlertType.WARNING, "Invalid Input");
               return;
           }
           
           // Perform search
           List<BusSchedule> results = busScheduleService.findSchedules(
               fromCity, toCity, date);
           
           // Update UI
           updateSearchResults(results);
       }
       
       private boolean validateSearchCriteria() {
           // Validation logic
       }
       
       private void updateSearchResults(List<BusSchedule> results) {
           // UI update logic
       }
   }
   ```

2. **Error Handling**
   - Use clear alert messages
   - Handle all edge cases
   - Provide user-friendly feedback
   - Log errors appropriately

3. **UI Updates**
   - Batch updates when possible
   - Use appropriate cell factories
   - Handle large datasets efficiently
   - Maintain responsive UI

### Service Layer Design

Services should implement these patterns:

1. **Business Logic Encapsulation**
   ```java
   @Service
   public class BusScheduleService {
       private final List<BusSchedule> schedules = new ArrayList<>();
       private final Map<String, String> busTypeFeatures = new HashMap<>();
       
       public List<BusSchedule> findSchedules(String from, String to, LocalDate date) {
           return schedules.stream()
               .filter(s -> s.getFromCity().equals(from) && 
                          s.getToCity().equals(to))
               .toList();
       }
       
       public Map<String, List<BusSchedule>> findAllSchedulesForDate(LocalDate date) {
           // Complex business logic implementation
       }
   }
   ```

2. **Data Management**
   - Use appropriate collections
   - Implement efficient search algorithms
   - Cache frequently accessed data
   - Handle transactions properly

3. **Feature Organization**
   - Group related functionality
   - Use clear method names
   - Document complex logic
   - Maintain single responsibility

### 2. Repository Interfaces

Repositories provide methods to access and manipulate database data. Spring Data JPA creates the implementation automatically.

**Example: BookingRepository.java**
```java
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findAllByOrderByBookingDateTimeDesc();
    
    long countByBookingDateTimeBetween(LocalDateTime start, LocalDateTime end);
    
    @Query(value = "SELECT * FROM bookings ORDER BY booking_date_time DESC LIMIT :limit", nativeQuery = true)
    List<Booking> findTopByOrderByBookingDateTimeDesc(@Param("limit") int limit);
}
```

Key points:
- Extends `JpaRepository` for common CRUD operations
- Defines custom query methods using naming conventions
- Uses `@Query` for more complex SQL queries

### 3. Service Classes

Services implement business logic and act as a bridge between controllers and repositories.

**Example: BookingService.java**
```java
@Service
public class BookingService {
    
    private final BookingRepository bookingRepository;
    
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByBookingDateTimeDesc();
    }
    
    public long getTotalBookings() {
        return bookingRepository.count();
    }
    
    // Additional methods...
}
```

Key points:
- `@Service` marks the class as a Spring service
- Dependency injection with `@Autowired`
- Implements business logic using repository methods

### 4. Controller Classes (JavaFX)

Controllers handle user interactions and connect the UI with the application logic.

**Example: BookingController.java**
```java
public class BookingController {
    
    private final BookingService bookingService;
    private final BusScheduleService busScheduleService;
    
    @FXML
    private ComboBox<String> fromCityCombo;
    
    @FXML
    private ComboBox<String> toCityCombo;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private TableView<BusSchedule> busTable;
    
    @Autowired
    public BookingController(BookingService bookingService, BusScheduleService busScheduleService) {
        this.bookingService = bookingService;
        this.busScheduleService = busScheduleService;
    }
    
    @FXML
    public void initialize() {
        // Initialize UI components
        fromCityCombo.getItems().addAll(busScheduleService.getAvailableCities());
        toCityCombo.getItems().addAll(busScheduleService.getAvailableCities());
        datePicker.setValue(LocalDate.now().plusDays(1));
        
        // Set up event handlers
        busTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleScheduleDetails(busTable.getSelectionModel().getSelectedItem());
            }
        });
    }
    
    @FXML
    private void handleSearch() {
        // Search logic...
    }
    
    // Additional methods...
}
```

Key points:
- Uses `@FXML` to connect with FXML file elements
- `initialize()` method sets up the UI
- Event handlers manage user interactions

### 5. FXML Files

FXML files define the UI layout and components in XML format.

**Example: BookingView.fxml (simplified)**
```xml
<VBox xmlns="http://javafx.com/javafx" xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.vexe.controller.BookingController"
      styleClass="booking-container">
    
    <HBox spacing="10" styleClass="search-container">
        <ComboBox fx:id="fromCityCombo" promptText="From"/>
        <ComboBox fx:id="toCityCombo" promptText="To"/>
        <DatePicker fx:id="datePicker"/>
        <Button text="Search" onAction="#handleSearch"/>
    </HBox>
    
    <TableView fx:id="busTable">
        <columns>
            <TableColumn fx:id="timeColumn" text="Time"/>
            <TableColumn fx:id="busTypeColumn" text="Bus Type"/>
            <TableColumn fx:id="priceColumn" text="Price"/>
            <TableColumn fx:id="seatsColumn" text="Available Seats"/>
        </columns>
    </TableView>
    
    <!-- Additional UI components -->
</VBox>
```

Key points:
- Defines the UI structure and components
- Links to the controller class
- Associates methods with UI actions

### 6. Database Migrations

Flyway migrations define and evolve the database schema.

**Example: V1__init_schema.sql**
```sql
-- Users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Additional tables...

-- Create indexes
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
-- Additional indexes...
```

Key points:
- Versioned SQL scripts for database changes
- Automatically applied by Flyway
- Ensures consistent database state

## Database Evolution

The database schema evolved during development, showing an important principle in software development: **iterative refinement**. This evolution demonstrates how applications often change as requirements are better understood:

1. **Initial Complex Schema (V1)**:
   - Full relational model with users, buses, routes, schedules, bookings
   - Foreign key relationships between tables
   - Many-to-many relationships

2. **Schema Simplification (V5)**:
   - Removed user authentication implementation
   - Simplified booking model
   - Focused on core functionality first

3. **Feature Enhancement (V7)**:
   - Added amenities for buses
   - Enhanced schedule information
   - Improved data model for better user experience

This evolution demonstrates an important principle: **start with working software, then evolve**. The initial complex design was simplified to get core functionality working, with plans to add more features later.

## Learning Path

For beginners learning Java and Spring Boot, here's a recommended learning path based on this application:

### 1. Java Fundamentals
- Object-oriented programming concepts
- Classes, interfaces, and inheritance
- Collections framework
- Exception handling

### 2. Spring Framework Basics
- Dependency injection
- Spring annotations
- Spring Boot configuration
- Application context

### 3. Data Access
- JPA and Hibernate basics
- Entity relationships
- Spring Data repositories
- Database migrations with Flyway

### 4. User Interface
- JavaFX components
- FXML and Scene Builder
- Event handling
- UI styling with CSS

### 5. Application Architecture
- Layered architecture
- Separation of concerns
- Service pattern
- MVC pattern

## Advanced Topics

Once you've mastered the basics, you can explore these advanced topics:

### 1. Authentication and Security
- The application has a `users` table but no authentication implementation
- This could be extended with Spring Security
- Adding login/registration functionality

### 2. Testing
- Unit testing with JUnit
- Integration testing
- Mock objects with Mockito

### 3. Advanced Database Features
- Transactions
- Performance optimization
- Query optimization

### 4. Deployment
- Application packaging
- Production deployment
- CI/CD pipelines

## Conclusion

The VeXe Bus Booking System demonstrates a practical application of Java, Spring Boot, and JavaFX. By studying its architecture and components, you can learn how these technologies work together to create a complete application.

Remember that real-world applications often evolve over time. This application shows both good design patterns to follow and areas where it could be improved or extended - both are valuable learning opportunities. 