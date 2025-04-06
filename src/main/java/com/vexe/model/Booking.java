package com.vexe.model;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bookings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private BusSchedule schedule;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "passenger_phone", nullable = false)
    private String passengerPhone;

    @Column(name = "booking_date_time", nullable = false)
    private LocalDateTime bookingDateTime;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String status;

    // JavaFX Properties
    @Transient
    private StringProperty seatNumberProperty;
    @Transient
    private StringProperty passengerNameProperty;
    @Transient
    private StringProperty passengerPhoneProperty;
    @Transient
    private StringProperty statusProperty;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BusSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(BusSchedule schedule) {
        this.schedule = schedule;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // JavaFX Property Getters
    public StringProperty seatNumberProperty() {
        if (seatNumberProperty == null) {
            seatNumberProperty = new SimpleStringProperty(seatNumber);
        }
        return seatNumberProperty;
    }

    public StringProperty passengerNameProperty() {
        if (passengerNameProperty == null) {
            passengerNameProperty = new SimpleStringProperty(passengerName);
        }
        return passengerNameProperty;
    }

    public StringProperty passengerPhoneProperty() {
        if (passengerPhoneProperty == null) {
            passengerPhoneProperty = new SimpleStringProperty(passengerPhone);
        }
        return passengerPhoneProperty;
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
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(user, booking.user) && Objects.equals(schedule, booking.schedule) && Objects.equals(seatNumber, booking.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, schedule, seatNumber);
    }
}