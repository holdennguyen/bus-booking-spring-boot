# VeXe Bus Booking System

A modern desktop application for bus ticket booking and management, built with Java Spring Boot and JavaFX.

## Features

- User authentication and management
- Bus route and schedule management
- Ticket booking system with real-time updates
- Order history tracking and analytics
- Modern Material Design UI with blue theme
- Responsive dashboard with booking statistics

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

## Prerequisites

- JDK 23 or later
- Maven 3.8+
- PostgreSQL 16+
- IDE (IntelliJ IDEA recommended)

## Setup & Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd vexe
```

2. Create PostgreSQL database:
```bash
createdb vexe
```

3. Configure database connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vexe
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn clean javafx:run
```

## UI Theme

The application uses a modern Material Design theme with a blue color scheme:
- Primary Color: #1976D2 (Material Blue)
- Secondary Color: #1565C0 (Darker Blue)
- Accent Color: #2196F3 (Light Blue)
- Background: #F5F5F5 (Light Gray)
- Text: #2C3E50 (Dark Gray)

## Project Structure

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

## Development Guidelines

1. Database Migrations
   - The project uses Flyway for database migrations
   - New migrations should be added to `src/main/resources/db/migration`
   - Follow the naming convention: `V{version}__{description}.sql`

2. UI Development
   - All styles are centralized in `src/main/resources/styles/main.css`
   - Follow the Material Design guidelines
   - Use the provided color scheme for consistency

3. Testing
   - Write unit tests for new features
   - Ensure all tests pass before committing: `mvn test`

## Troubleshooting

1. Database Connection Issues
   - Verify PostgreSQL is running: `pg_isready`
   - Check database exists: `psql -l | grep vexe`
   - Ensure correct credentials in `application.properties`

2. Build/Run Issues
   - Clear Maven cache: `mvn clean`
   - Update dependencies: `mvn dependency:resolve`
   - Check Java version: `java -version`

## Contributing

1. Pull the latest changes:
```bash
git pull origin main
```

2. Create a new branch:
```bash
git checkout -b feature/your-feature-name
```

3. Make your changes and test locally
4. Push your changes:
```bash
git push origin feature/your-feature-name
```

5. Create a Pull Request

## Project Cleanup

The project has been cleaned up to remove unnecessary files and directories:
- Removed development tool files (`.cursor/`, `.xnotes/`)
- Removed empty directories (`security/`, `util/`)
- Updated `.gitignore` to exclude:
  - IDE-specific files
  - Build artifacts
  - OS-generated files
  - Local environment files
  - Temporary files

## License

This project is licensed under the MIT License - see the LICENSE file for details. 