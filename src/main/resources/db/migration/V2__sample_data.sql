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
('Ho Chi Minh City', 'Nha Trang', 450.00, 480);

-- Sample Schedules (for the next 7 days)
INSERT INTO schedules (route_id, bus_id, departure_time, arrival_time, price, available_seats)
SELECT 
    r.id,
    b.id,
    CURRENT_DATE + (n || ' days')::INTERVAL + '08:00:00'::TIME,
    CURRENT_DATE + (n || ' days')::INTERVAL + '08:00:00'::TIME + (r.duration || ' minutes')::INTERVAL,
    CASE 
        WHEN b.bus_type = 'LUXURY' THEN r.distance * 1000
        ELSE r.distance * 800
    END,
    b.capacity
FROM routes r
CROSS JOIN buses b
CROSS JOIN generate_series(0, 7) n
WHERE (r.id + b.id + n) % 2 = 0;

-- Sample Bookings
INSERT INTO bookings (user_id, schedule_id, seat_number, status, total_price)
SELECT 
    u.id,
    s.id,
    floor(random() * b.capacity) + 1,
    'CONFIRMED',
    s.price
FROM schedules s
CROSS JOIN users u
CROSS JOIN buses b
WHERE s.bus_id = b.id
AND s.departure_time > CURRENT_TIMESTAMP
LIMIT 10;

-- Update available seats for booked schedules
UPDATE schedules s
SET available_seats = available_seats - (
    SELECT COUNT(*)
    FROM bookings b
    WHERE b.schedule_id = s.id
)
WHERE id IN (SELECT DISTINCT schedule_id FROM bookings); 