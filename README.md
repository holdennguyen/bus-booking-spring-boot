# Vexe Bus Booking System

A desktop application for bus ticket booking and order history tracking built with Java Spring Boot and JavaFX.

## Features

- User authentication and management
- Bus route and schedule management
- Ticket booking system
- Order history tracking
- Sample data for demonstration

## Prerequisites

- JDK 17 or later
- Maven 3.8+
- PostgreSQL 15+
- IDE (IntelliJ IDEA recommended)

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd vexe
```

2. Create PostgreSQL database:
```bash
createdb vexe_db
```

3. Configure database connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vexe_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

## Sample Data

The application comes with sample data for demonstration purposes:
- Users: john.doe/password, jane.smith/password
- Bus routes between major cities
- Sample schedules for the next 7 days
- Sample bookings

## Project Structure

```
src/main/java/com/vexe/
├── config/         # Configuration classes
├── controller/     # REST controllers
├── model/         # Entity classes
├── repository/    # Data access layer
├── service/       # Business logic
├── security/      # Security configuration
├── ui/            # JavaFX views
└── util/          # Utility classes
```

## Development

- The project uses Flyway for database migrations
- Sample data is loaded automatically on first run
- JavaFX is used for the desktop UI
- Spring Security handles authentication

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 