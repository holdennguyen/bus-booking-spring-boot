-- Clear existing schedules
TRUNCATE TABLE bus_schedules CASCADE;

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