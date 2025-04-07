package com.vexe.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bus_schedules")
public class BusSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String time;
    private String fromCity;
    private String toCity;
    private String busType;
    private int availableSeats;
    private double price;
    private int estimatedDuration; // in minutes
    
    @ElementCollection
    @CollectionTable(name = "bus_schedule_amenities", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "amenity")
    private List<String> amenities;
    
    private String routeInfo; // Additional stops or route details
    
    public BusSchedule(String time, String fromCity, String toCity, String busType, 
                      int availableSeats, double price, int estimatedDuration,
                      List<String> amenities, String routeInfo) {
        this.time = time;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.busType = busType;
        this.availableSeats = availableSeats;
        this.price = price;
        this.estimatedDuration = estimatedDuration;
        this.amenities = amenities;
        this.routeInfo = routeInfo;
    }
    
    public boolean hasAmenity(String amenity) {
        return amenities != null && amenities.contains(amenity);
    }
}