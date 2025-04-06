CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(255),
    role VARCHAR(50) NOT NULL
);

-- Insert default admin user with password 'admin' (BCrypt hashed)
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES ('admin', 'admin@vexe.com', '$2a$10$rS.UZBXz7gL1.nEHFZ6QVeWHh1hxyXoRy4z.89MZPUF9B8VU0P6Uy', 'Admin', 'User', 'ADMIN'); 