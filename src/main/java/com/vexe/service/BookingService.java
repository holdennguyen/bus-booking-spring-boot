package com.vexe.service;

import com.vexe.model.Booking;
import com.vexe.model.BusSchedule;
import com.vexe.model.User;
import com.vexe.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BusScheduleService busScheduleService;

    @Transactional
    public Booking createBooking(Long scheduleId, User user, String seatNumber, 
                               String passengerName, String passengerPhone) {
        BusSchedule schedule = busScheduleService.getScheduleById(scheduleId);

        if (schedule.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available for this schedule");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSchedule(schedule);
        booking.setSeatNumber(seatNumber);
        booking.setPassengerName(passengerName);
        booking.setPassengerPhone(passengerPhone);
        booking.setBookingDateTime(LocalDateTime.now());
        booking.setTotalPrice(schedule.getPrice());
        booking.setStatus("CONFIRMED");

        schedule.setAvailableSeats(schedule.getAvailableSeats() - 1);
        busScheduleService.updateSchedule(scheduleId, schedule);

        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(User user) {
        return bookingRepository.findByUserOrderByBookingDateTimeDesc(user);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Transactional
    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        BusSchedule schedule = booking.getSchedule();

        booking.setStatus("CANCELLED");
        schedule.setAvailableSeats(schedule.getAvailableSeats() + 1);

        busScheduleService.updateSchedule(schedule.getId(), schedule);
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsBySchedule(BusSchedule schedule) {
        return bookingRepository.findBySchedule(schedule);
    }

    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    public List<Booking> getBookingsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.findByBookingDateTimeBetween(startDate, endDate);
    }

    public List<Booking> getUserBookingsByDateRange(User user, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.findUserBookingsByDateRange(user, startDate, endDate);
    }

    public List<Booking> getBookingsByScheduleAndStatus(BusSchedule schedule, String status) {
        return bookingRepository.findBookingsByScheduleAndStatus(schedule, status);
    }
} 