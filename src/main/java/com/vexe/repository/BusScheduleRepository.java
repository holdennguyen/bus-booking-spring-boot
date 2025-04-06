package com.vexe.repository;

import com.vexe.model.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {
    List<BusSchedule> findByFromCityAndToCity(String fromCity, String toCity);
    
    List<BusSchedule> findByFromCityAndToCityAndDepartureTimeGreaterThanEqual(
            String fromCity, String toCity, LocalDateTime departureTime);
    
    List<BusSchedule> findByBusType(String busType);
    
    List<BusSchedule> findByStatus(String status);
    
    List<BusSchedule> findByDepartureTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT DISTINCT s.fromCity FROM BusSchedule s UNION SELECT DISTINCT s.toCity FROM BusSchedule s")
    List<String> findDistinctCities();
} 