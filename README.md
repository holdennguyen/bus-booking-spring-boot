# VeXe Bus Booking System

A modern desktop application for bus ticket booking and management, built with Java Spring Boot and JavaFX.

## Quick Start

1. **Prerequisites**
   - JDK 23 or later
   - Maven 3.8+
   - PostgreSQL 16+

2. **Installation**
   ```bash
   # Clone repository
   git clone https://github.com/holdennguyen/bus-booking-spring-boot.git
   cd bus-booking-spring-boot

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

## Documentation

For detailed technical documentation, please refer to [DOCUMENTATION.md](DOCUMENTATION.md) which includes:
- System Architecture
- Database Design
- UI Components
- Features and Functionality
- Development Setup
- Code Structure
- Testing and Deployment
- Troubleshooting

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

## Contributing

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 