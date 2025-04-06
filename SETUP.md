# VeXe Bus Booking System - Local Development Setup Guide

This guide will help you set up the VeXe Bus Booking System project on your local machine using NetBeans IDE and PostgreSQL.

## Prerequisites

1. **Java Development Kit (JDK)**
   - Install JDK 21 or later
   - Download from: https://adoptium.net/
   - Set JAVA_HOME environment variable

2. **NetBeans IDE**
   - Download NetBeans 19 or later
   - Download from: https://netbeans.apache.org/
   - Ensure it's configured to use JDK 21

3. **PostgreSQL**
   - Install PostgreSQL 16
   - Download from: https://www.postgresql.org/download/
   - Remember your postgres user password during installation

4. **Maven**
   - NetBeans includes Maven, but you can install it separately
   - Download from: https://maven.apache.org/
   - Minimum version required: 3.8+

## Project Setup

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/vexe.git
cd vexe
```

### 2. Database Setup
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

### 3. NetBeans Setup
1. Open NetBeans
2. Go to File → Open Project
3. Navigate to the cloned vexe directory
4. Select the project and click Open
5. Right-click on the project → Properties
   - Ensure Java platform is set to JDK 21
   - Verify Maven settings are correct

### 4. Build and Run
1. Right-click on the project
2. Select "Clean and Build"
3. Once build is successful, click "Run"
   - Or use: Run → Run Project

The application should start and automatically:
- Create database tables
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

### Runtime Issues
1. Port conflicts:
   - Check if PostgreSQL is running on default port 5432
   - Verify no other application is using required ports

2. Memory issues:
   - Increase NetBeans heap size:
     - Open netbeans.conf
     - Modify -Xmx value

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

## Need Help?
- Check the project documentation in `/docs`
- Review migration files in `src/main/resources/db/migration`
- Contact the team lead for access issues
- Create an issue in GitHub for technical problems 