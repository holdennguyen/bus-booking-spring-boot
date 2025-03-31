package com.vexe.repository;

import com.vexe.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findAllByOrderByBookingDateTimeDesc();
    
    long countByBookingDateTimeBetween(LocalDateTime start, LocalDateTime end);
    
    @Query(value = "SELECT * FROM bookings ORDER BY booking_date_time DESC LIMIT :limit", nativeQuery = true)
    List<Booking> findTopByOrderByBookingDateTimeDesc(@Param("limit") int limit);
} 