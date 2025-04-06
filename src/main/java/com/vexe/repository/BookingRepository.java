package com.vexe.repository;

import com.vexe.model.Booking;
import com.vexe.model.BusSchedule;
import com.vexe.model.User;
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

    List<Booking> findByUserOrderByBookingDateTimeDesc(User user);
    List<Booking> findBySchedule(BusSchedule schedule);
    List<Booking> findByStatus(String status);
    List<Booking> findByBookingDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT b FROM Booking b WHERE b.user = :user AND b.bookingDateTime >= :startDate AND b.bookingDateTime <= :endDate")
    List<Booking> findUserBookingsByDateRange(@Param("user") User user, 
                                             @Param("startDate") LocalDateTime startDate, 
                                             @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT b FROM Booking b WHERE b.schedule = :schedule AND b.status = :status")
    List<Booking> findBookingsByScheduleAndStatus(@Param("schedule") BusSchedule schedule, 
                                                 @Param("status") String status);
} 