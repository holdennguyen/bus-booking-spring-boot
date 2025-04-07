package com.vexe.model;

import jakarta.persistence.*;
import javafx.beans.property.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "from_city", nullable = false)
    private String fromCity;
    
    @Column(name = "to_city", nullable = false)
    private String toCity;
    
    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;
    
    @Column(name = "travel_time", nullable = false)
    private LocalTime travelTime;
    
    @Column(name = "bus_type", nullable = false)
    private String busType;
    
    @Column(name = "passenger_count", nullable = false)
    private Integer passengerCount;
    
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    
    @Column(name = "booking_date_time", nullable = false)
    private LocalDateTime bookingDateTime;
    
    @Transient
    private final StringProperty bookingDateTimeProperty = new SimpleStringProperty();
    
    @PostLoad
    private void updateProperties() {
        bookingDateTimeProperty.set(bookingDateTime.toString());
    }
    
    @PrePersist
    protected void onCreate() {
        if (bookingDateTime == null) {
            bookingDateTime = LocalDateTime.now();
            bookingDateTimeProperty.set(bookingDateTime.toString());
        }
    }
    
    public Booking(String fromCity, String toCity, LocalDate travelDate, LocalTime travelTime,
                  String busType, int passengerCount, BigDecimal totalPrice) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.travelDate = travelDate;
        this.travelTime = travelTime;
        this.busType = busType;
        this.passengerCount = passengerCount;
        this.totalPrice = totalPrice;
        this.bookingDateTime = LocalDateTime.now();
        this.bookingDateTimeProperty.set(bookingDateTime.toString());
    }
} 