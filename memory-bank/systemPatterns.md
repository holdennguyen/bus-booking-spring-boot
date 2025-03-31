# System Patterns

## Architecture Overview
```mermaid
graph TD
    UI[JavaFX UI Layer] --> |User Interaction| C[Controllers]
    C --> |Business Logic| S[Services]
    S --> |Data Access| R[Repositories]
    R --> |Persistence| DB[(PostgreSQL)]
    
    style UI fill:#f9f,stroke:#333,stroke-width:2px
    style C fill:#bbf,stroke:#333,stroke-width:2px
    style S fill:#dfd,stroke:#333,stroke-width:2px
    style R fill:#fdd,stroke:#333,stroke-width:2px
    style DB fill:#ddd,stroke:#333,stroke-width:2px
```

## Design Patterns
### Architectural Patterns
- **MVC Pattern**: Separation of UI, business logic, and data
- **Repository Pattern**: Data access abstraction
- **Service Layer Pattern**: Business logic encapsulation
- **DTO Pattern**: Data transfer between layers

### Implementation Patterns
- **Builder Pattern**: For complex object construction
- **Factory Pattern**: For object creation
- **Singleton Pattern**: For shared resources
- **Observer Pattern**: For UI updates

## Component Relationships
### Core Components
```mermaid
graph LR
    A[Authentication] --> B[Booking System]
    B --> C[Order History]
    B --> D[Bus Management]
    
    style A fill:#f9f,stroke:#333,stroke-width:2px
    style B fill:#bbf,stroke:#333,stroke-width:2px
    style C fill:#dfd,stroke:#333,stroke-width:2px
    style D fill:#fdd,stroke:#333,stroke-width:2px
```

## Data Flow
### Booking Flow
```mermaid
sequenceDiagram
    participant U as User
    participant UI as JavaFX UI
    participant S as Service
    participant DB as Database
    
    U->>UI: Select Route
    UI->>S: Request Available Seats
    S->>DB: Query Seats
    DB-->>S: Return Seats
    S-->>UI: Display Seats
    U->>UI: Book Seat
    UI->>S: Process Booking
    S->>DB: Save Booking
    DB-->>S: Confirm
    S-->>UI: Show Confirmation
```

## Security Patterns
- Basic authentication for prototype
- Password encryption
- Session management
- Role-based access control

## Notes
- Architecture focuses on maintainability and extensibility
- Clear separation of concerns between layers
- Modular design for easy feature addition
- Emphasis on data consistency and transaction management
- This document will be updated as we make architectural decisions
- All technical decisions should be documented here
- Patterns should be consistent across the system 