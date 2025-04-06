package com.vexe.service;

import com.vexe.model.BusSchedule;
import com.vexe.repository.BusScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusScheduleService {

    @Autowired
    private BusScheduleRepository busScheduleRepository;

    public List<BusSchedule> getAllSchedules() {
        return busScheduleRepository.findAll();
    }

    public BusSchedule getScheduleById(Long id) {
        return busScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public List<BusSchedule> findSchedules(String fromCity, String toCity, LocalDateTime departureTime) {
        return busScheduleRepository.findByFromCityAndToCityAndDepartureTimeGreaterThanEqual(
                fromCity, toCity, departureTime);
    }

    public List<BusSchedule> findSchedulesByBusType(String busType) {
        return busScheduleRepository.findByBusType(busType);
    }

    public List<String> getAllCities() {
        return busScheduleRepository.findDistinctCities();
    }

    @Transactional
    public BusSchedule createSchedule(BusSchedule schedule) {
        return busScheduleRepository.save(schedule);
    }

    @Transactional
    public BusSchedule updateSchedule(Long id, BusSchedule schedule) {
        BusSchedule existingSchedule = getScheduleById(id);
        existingSchedule.setFromCity(schedule.getFromCity());
        existingSchedule.setToCity(schedule.getToCity());
        existingSchedule.setDepartureTime(schedule.getDepartureTime());
        existingSchedule.setArrivalTime(schedule.getArrivalTime());
        existingSchedule.setBusType(schedule.getBusType());
        existingSchedule.setTotalSeats(schedule.getTotalSeats());
        existingSchedule.setAvailableSeats(schedule.getAvailableSeats());
        existingSchedule.setPrice(schedule.getPrice());
        existingSchedule.setStatus(schedule.getStatus());
        return busScheduleRepository.save(existingSchedule);
    }

    @Transactional
    public void deleteSchedule(Long id) {
        busScheduleRepository.deleteById(id);
    }

    public List<BusSchedule> searchSchedules(String fromCity, String toCity, LocalDateTime departureTime) {
        return busScheduleRepository.findByFromCityAndToCityAndDepartureTimeGreaterThanEqual(
                fromCity, toCity, departureTime);
    }

    public List<BusSchedule> getSchedulesByStatus(String status) {
        return busScheduleRepository.findByStatus(status);
    }

    public List<BusSchedule> getSchedulesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return busScheduleRepository.findByDepartureTimeBetween(startDate, endDate);
    }
} 