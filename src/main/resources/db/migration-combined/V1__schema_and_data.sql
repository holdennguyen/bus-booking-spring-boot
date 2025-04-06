-- ===============================================
-- SCHEMA CREATION
-- ===============================================

-- Drop tables if they exist to ensure clean setup
DROP TABLE IF EXISTS bus_schedule_amenities CASCADE;
DROP TABLE IF EXISTS bus_schedules CASCADE;
DROP TABLE IF EXISTS bookings CASCADE;
DROP TABLE IF EXISTS routes CASCADE;
DROP TABLE IF EXISTS buses CASCADE;
DROP TABLE IF EXISTS users CASCADE;

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

-- Buses table
CREATE TABLE buses (
    id BIGSERIAL PRIMARY KEY,
    bus_number VARCHAR(20) NOT NULL UNIQUE,
    capacity INTEGER NOT NULL,
    bus_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Routes table
CREATE TABLE routes (
    id BIGSERIAL PRIMARY KEY,
    origin VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    distance DECIMAL(10,2) NOT NULL,
    duration INTEGER NOT NULL, -- in minutes
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(origin, destination)
);

-- Bookings table (updated structure)
CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    from_city VARCHAR(100),
    to_city VARCHAR(100),
    travel_date DATE,
    travel_time TIME,
    bus_type VARCHAR(50),
    passenger_count INTEGER,
    total_price DECIMAL(10,2),
    booking_date_time TIMESTAMP
);

-- Bus Schedules table
CREATE TABLE bus_schedules (
    id BIGSERIAL PRIMARY KEY,
    bus_type VARCHAR(50) NOT NULL,
    from_city VARCHAR(100) NOT NULL,
    to_city VARCHAR(100) NOT NULL,
    time TIME NOT NULL,
    estimated_duration INTEGER NOT NULL, -- in minutes
    available_seats INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    route_info VARCHAR(100),
    has_wifi BOOLEAN DEFAULT FALSE,
    hasusbpower BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Bus Schedule Amenities table
CREATE TABLE bus_schedule_amenities (
    id BIGSERIAL PRIMARY KEY,
    schedule_id BIGINT NOT NULL REFERENCES bus_schedules(id) ON DELETE CASCADE,
    amenity VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_buses_number ON buses(bus_number);
CREATE INDEX idx_routes_origin_dest ON routes(origin, destination);
CREATE INDEX idx_bookings_from_to ON bookings(from_city, to_city);
CREATE INDEX idx_bookings_travel_date ON bookings(travel_date);
CREATE INDEX idx_bus_schedules_from_to ON bus_schedules(from_city, to_city);
CREATE INDEX idx_bus_schedule_amenities_schedule ON bus_schedule_amenities(schedule_id);

-- ===============================================
-- SAMPLE DATA
-- ===============================================

-- Sample Users (password is 'password' encoded with BCrypt)
INSERT INTO users (username, password, email, full_name, phone) VALUES
('john.doe', '$2a$10$Xl0yhVzxRgukUQKGTQKUfO8YqGRVXKzUVHxR9uZzKNZb7eHGQ5TZi', 'john.doe@example.com', 'John Doe', '1234567890'),
('jane.smith', '$2a$10$Xl0yhVzxRgukUQKGTQKUfO8YqGRVXKzUVHxR9uZzKNZb7eHGQ5TZi', 'jane.smith@example.com', 'Jane Smith', '0987654321');

-- Sample Buses
INSERT INTO buses (bus_number, capacity, bus_type) VALUES
('BUS001', 45, 'LUXURY'),
('BUS002', 40, 'STANDARD'),
('BUS003', 45, 'LUXURY'),
('BUS004', 40, 'STANDARD');

-- Sample Routes
INSERT INTO routes (origin, destination, distance, duration) VALUES
('Ho Chi Minh City', 'Ha Noi', 1600.00, 1440),
('Ho Chi Minh City', 'Da Nang', 850.00, 720),
('Ha Noi', 'Da Nang', 750.00, 660),
('Ho Chi Minh City', 'Nha Trang', 450.00, 480),
('Ho Chi Minh City', 'Da Lat', 300.00, 420),
('Nha Trang', 'Da Lat', 135.00, 180);

-- Insert sample bus schedules for April 2025
INSERT INTO bus_schedules (bus_type, from_city, to_city, time, estimated_duration, available_seats, price, route_info, has_wifi, hasusbpower) VALUES
-- Ho Chi Minh City to Da Lat
('VIP', 'Ho Chi Minh City', 'Da Lat', '07:00', 480, 30, 450000, 'Via CT.01 - Direct Route', true, true),
('Standard', 'Ho Chi Minh City', 'Da Lat', '08:30', 510, 35, 350000, 'Via CT.01 - Regular Route', false, true),
('Luxury', 'Ho Chi Minh City', 'Da Lat', '10:00', 450, 25, 550000, 'Via CT.01 - Express Route', true, true),
('Standard', 'Ho Chi Minh City', 'Da Lat', '13:30', 510, 35, 350000, 'Via CT.01 - Regular Route', false, true),
('VIP', 'Ho Chi Minh City', 'Da Lat', '15:00', 480, 30, 450000, 'Via CT.01 - Direct Route', true, true),
('Luxury', 'Ho Chi Minh City', 'Da Lat', '20:00', 450, 25, 550000, 'Via CT.01 - Express Route', true, true),

-- Da Lat to Ho Chi Minh City
('VIP', 'Da Lat', 'Ho Chi Minh City', '07:30', 480, 30, 450000, 'Via CT.01 - Direct Route', true, true),
('Standard', 'Da Lat', 'Ho Chi Minh City', '09:00', 510, 35, 350000, 'Via CT.01 - Regular Route', false, true),
('Luxury', 'Da Lat', 'Ho Chi Minh City', '11:30', 450, 25, 550000, 'Via CT.01 - Express Route', true, true),
('Standard', 'Da Lat', 'Ho Chi Minh City', '14:00', 510, 35, 350000, 'Via CT.01 - Regular Route', false, true),
('VIP', 'Da Lat', 'Ho Chi Minh City', '16:30', 480, 30, 450000, 'Via CT.01 - Direct Route', true, true),
('Luxury', 'Da Lat', 'Ho Chi Minh City', '21:00', 450, 25, 550000, 'Via CT.01 - Express Route', true, true),

-- Ho Chi Minh City to Nha Trang
('VIP', 'Ho Chi Minh City', 'Nha Trang', '06:00', 600, 30, 550000, 'Via CT.01 - Coastal Route', true, true),
('Standard', 'Ho Chi Minh City', 'Nha Trang', '08:00', 660, 35, 450000, 'Via CT.01 - Regular Route', false, true),
('Luxury', 'Ho Chi Minh City', 'Nha Trang', '10:30', 570, 25, 650000, 'Via CT.01 - Express Route', true, true),
('Standard', 'Ho Chi Minh City', 'Nha Trang', '14:30', 660, 35, 450000, 'Via CT.01 - Regular Route', false, true),
('VIP', 'Ho Chi Minh City', 'Nha Trang', '17:00', 600, 30, 550000, 'Via CT.01 - Coastal Route', true, true),
('Luxury', 'Ho Chi Minh City', 'Nha Trang', '20:30', 570, 25, 650000, 'Via CT.01 - Express Route', true, true),

-- Nha Trang to Ho Chi Minh City
('VIP', 'Nha Trang', 'Ho Chi Minh City', '06:30', 600, 30, 550000, 'Via CT.01 - Coastal Route', true, true),
('Standard', 'Nha Trang', 'Ho Chi Minh City', '09:00', 660, 35, 450000, 'Via CT.01 - Regular Route', false, true),
('Luxury', 'Nha Trang', 'Ho Chi Minh City', '11:30', 570, 25, 650000, 'Via CT.01 - Express Route', true, true),
('Standard', 'Nha Trang', 'Ho Chi Minh City', '15:00', 660, 35, 450000, 'Via CT.01 - Regular Route', false, true),
('VIP', 'Nha Trang', 'Ho Chi Minh City', '17:30', 600, 30, 550000, 'Via CT.01 - Coastal Route', true, true),
('Luxury', 'Nha Trang', 'Ho Chi Minh City', '21:00', 570, 25, 650000, 'Via CT.01 - Express Route', true, true),

-- Da Lat to Nha Trang
('VIP', 'Da Lat', 'Nha Trang', '07:00', 240, 30, 300000, 'Via QL27C - Mountain Route', true, true),
('Standard', 'Da Lat', 'Nha Trang', '09:30', 270, 35, 250000, 'Via QL27C - Regular Route', false, true),
('Luxury', 'Da Lat', 'Nha Trang', '12:00', 210, 25, 350000, 'Via QL27C - Express Route', true, true),
('Standard', 'Da Lat', 'Nha Trang', '14:30', 270, 35, 250000, 'Via QL27C - Regular Route', false, true),
('VIP', 'Da Lat', 'Nha Trang', '16:00', 240, 30, 300000, 'Via QL27C - Mountain Route', true, true),
('Luxury', 'Da Lat', 'Nha Trang', '19:00', 210, 25, 350000, 'Via QL27C - Express Route', true, true),

-- Nha Trang to Da Lat
('VIP', 'Nha Trang', 'Da Lat', '08:00', 240, 30, 300000, 'Via QL27C - Mountain Route', true, true),
('Standard', 'Nha Trang', 'Da Lat', '10:30', 270, 35, 250000, 'Via QL27C - Regular Route', false, true),
('Luxury', 'Nha Trang', 'Da Lat', '13:00', 210, 25, 350000, 'Via QL27C - Express Route', true, true),
('Standard', 'Nha Trang', 'Da Lat', '15:30', 270, 35, 250000, 'Via QL27C - Regular Route', false, true),
('VIP', 'Nha Trang', 'Da Lat', '17:00', 240, 30, 300000, 'Via QL27C - Mountain Route', true, true),
('Luxury', 'Nha Trang', 'Da Lat', '20:00', 210, 25, 350000, 'Via QL27C - Express Route', true, true);

-- Insert bus amenities
INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Air Conditioning' FROM bus_schedules;

INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Comfortable Seats' FROM bus_schedules;

INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Reading Light' FROM bus_schedules;

-- Add extra amenities for VIP buses
INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Snack Service' FROM bus_schedules WHERE bus_type = 'VIP';

INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Personal TV' FROM bus_schedules WHERE bus_type = 'VIP';

-- Add extra amenities for Luxury buses
INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Premium Snack Service' FROM bus_schedules WHERE bus_type = 'Luxury';

INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Personal TV' FROM bus_schedules WHERE bus_type = 'Luxury';

INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Massage Seats' FROM bus_schedules WHERE bus_type = 'Luxury';

INSERT INTO bus_schedule_amenities (schedule_id, amenity) 
SELECT id, 'Blanket & Pillow' FROM bus_schedules WHERE bus_type = 'Luxury'; 