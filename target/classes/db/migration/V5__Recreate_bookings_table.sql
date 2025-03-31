DROP TABLE IF EXISTS bookings CASCADE;

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