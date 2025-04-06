package com.vexe.model;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus_schedules")
public class BusSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_city", nullable = false)
    private String fromCity;

    @Column(name = "to_city", nullable = false)
    private String toCity;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "bus_type", nullable = false)
    private String busType;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String status;

    // JavaFX Properties
    @Transient
    private StringProperty fromCityProperty;
    @Transient
    private StringProperty toCityProperty;
    @Transient
    private StringProperty busTypeProperty;
    @Transient
    private StringProperty statusProperty;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // JavaFX Property Getters
    public StringProperty fromCityProperty() {
        if (fromCityProperty == null) {
            fromCityProperty = new SimpleStringProperty(fromCity);
        }
        return fromCityProperty;
    }

    public StringProperty toCityProperty() {
        if (toCityProperty == null) {
            toCityProperty = new SimpleStringProperty(toCity);
        }
        return toCityProperty;
    }

    public StringProperty busTypeProperty() {
        if (busTypeProperty == null) {
            busTypeProperty = new SimpleStringProperty(busType);
        }
        return busTypeProperty;
    }

    public StringProperty statusProperty() {
        if (statusProperty == null) {
            statusProperty = new SimpleStringProperty(status);
        }
        return statusProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusSchedule that = (BusSchedule) o;
        return Objects.equals(id, that.id) && Objects.equals(fromCity, that.fromCity) && Objects.equals(toCity, that.toCity) && Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromCity, toCity, departureTime);
    }
}