package com.vexe.service;

import com.vexe.model.Booking;
import com.vexe.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {
    
    private final BookingRepository bookingRepository;
    
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByBookingDateTimeDesc();
    }
    
    public long getTotalBookings() {
        return bookingRepository.count();
    }
    
    public long getTodayBookings() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        return bookingRepository.countByBookingDateTimeBetween(startOfDay, endOfDay);
    }
    
    public Map.Entry<String, Long> getMostPopularRoute() {
        List<Booking> bookings = bookingRepository.findAll();
        Map<String, Long> routeCounts = bookings.stream()
            .collect(Collectors.groupingBy(
                booking -> booking.getFromCity() + " → " + booking.getToCity(),
                Collectors.counting()
            ));
        
        return routeCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()))
            .orElse(null);
    }
    
    public List<Booking> getRecentBookings(int limit) {
        return bookingRepository.findTopByOrderByBookingDateTimeDesc(limit);
    }
    
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
} 