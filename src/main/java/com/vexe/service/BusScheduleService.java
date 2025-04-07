package com.vexe.service;

import com.vexe.model.BusSchedule;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class BusScheduleService {
    private final List<BusSchedule> schedules = new ArrayList<>();
    private final Map<String, String> busTypeFeatures = new HashMap<>();
    private final Map<String, Double> busTypePriceRanges = new HashMap<>();
    
    public BusScheduleService() {
        // Initialize sample schedules
        schedules.add(new BusSchedule("06:00", "Ho Chi Minh", "Da Lat", "Luxury", 30, 450000, 420,
            Arrays.asList("Air Conditioning", "Reclining Seats", "Blanket", "Snacks", "WiFi", "USB Power"),
            "Via Dau Giay - Bao Loc"));
        
        schedules.add(new BusSchedule("07:30", "Ho Chi Minh", "Da Lat", "Standard", 45, 280000, 450,
            Arrays.asList("Air Conditioning", "Standard Seats"),
            "Via Dau Giay - Bao Loc"));
        
        schedules.add(new BusSchedule("09:00", "Ho Chi Minh", "Nha Trang", "VIP", 20, 550000, 510,
            Arrays.asList("Air Conditioning", "Massage Seats", "Blanket", "Meals", "Personal TV", "WiFi", "USB Power"),
            "Via Dau Giay - Phan Thiet - Cam Ranh"));
        
        schedules.add(new BusSchedule("10:30", "Ho Chi Minh", "Vung Tau", "Standard", 35, 150000, 180,
            Arrays.asList("Air Conditioning", "Standard Seats"),
            "Direct Route"));
        
        schedules.add(new BusSchedule("12:00", "Ho Chi Minh", "Da Lat", "VIP", 25, 500000, 420,
            Arrays.asList("Air Conditioning", "Massage Seats", "Blanket", "Meals", "WiFi", "USB Power"),
            "Via Dau Giay - Bao Loc"));
        
        schedules.add(new BusSchedule("14:00", "Ho Chi Minh", "Nha Trang", "Luxury", 28, 450000, 510,
            Arrays.asList("Air Conditioning", "Reclining Seats", "Blanket", "Snacks", "WiFi", "USB Power"),
            "Via Dau Giay - Phan Thiet - Cam Ranh"));
        
        schedules.add(new BusSchedule("16:00", "Ho Chi Minh", "Da Lat", "Standard", 40, 280000, 450,
            Arrays.asList("Air Conditioning", "Standard Seats"),
            "Via Dau Giay - Bao Loc"));
        
        // Initialize bus type features
        busTypeFeatures.put("Standard", """
            • Standard comfortable seats
            • Air conditioning
            • Basic amenities
            • Regular stops
            """);
        
        busTypeFeatures.put("Luxury", """
            • Spacious reclining seats
            • Premium air conditioning
            • Complimentary snacks and drinks
            • WiFi and USB charging
            • Fewer stops
            """);
        
        busTypeFeatures.put("VIP", """
            • Premium massage seats
            • Individual entertainment systems
            • Full meal service
            • WiFi and USB charging
            • Premium amenities
            • Express service with minimal stops
            """);
        
        // Initialize price ranges
        busTypePriceRanges.put("Standard", 280000.0);
        busTypePriceRanges.put("Luxury", 450000.0);
        busTypePriceRanges.put("VIP", 550000.0);
    }
    
    public List<String> getAvailableCities() {
        Set<String> cities = new HashSet<>();
        for (BusSchedule schedule : schedules) {
            cities.add(schedule.getFromCity());
            cities.add(schedule.getToCity());
        }
        List<String> sortedCities = new ArrayList<>(cities);
        Collections.sort(sortedCities);
        return sortedCities;
    }
    
    public List<String> getBusTypes() {
        return new ArrayList<>(busTypeFeatures.keySet());
    }
    
    public Map<String, String> getBusTypeFeatures() {
        return busTypeFeatures;
    }
    
    public Map<String, Double> getBusTypePriceRanges() {
        return busTypePriceRanges;
    }
    
    public List<BusSchedule> findSchedules(String from, String to, LocalDate date) {
        return schedules.stream()
            .filter(s -> s.getFromCity().equals(from) && s.getToCity().equals(to))
            .toList();
    }
    
    public Map<String, List<BusSchedule>> findAllSchedulesForDate(LocalDate date) {
        Map<String, List<BusSchedule>> allSchedules = new HashMap<>();
        List<String> cities = getAvailableCities();
        
        for (String fromCity : cities) {
            for (String toCity : cities) {
                if (!fromCity.equals(toCity)) {
                    String route = fromCity + " → " + toCity;
                    allSchedules.put(route, findSchedules(fromCity, toCity, date));
                }
            }
        }
        
        return allSchedules;
    }
    
    public List<BusSchedule> findSchedulesByBusType(String busType, LocalDate date) {
        return findSchedules("Ho Chi Minh City", "Ha Noi", date).stream()
            .filter(schedule -> schedule.getBusType().equals(busType))
            .toList();
    }
} 