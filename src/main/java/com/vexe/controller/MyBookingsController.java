package com.vexe.controller;

import com.vexe.model.Booking;
import com.vexe.service.BookingService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class MyBookingsController {
    
    @FXML private TableView<Booking> bookingsTable;
    @FXML private TableColumn<Booking, String> fromColumn;
    @FXML private TableColumn<Booking, String> toColumn;
    @FXML private TableColumn<Booking, String> dateColumn;
    @FXML private TableColumn<Booking, String> timeColumn;
    @FXML private TableColumn<Booking, String> busTypeColumn;
    @FXML private TableColumn<Booking, Integer> passengersColumn;
    @FXML private TableColumn<Booking, BigDecimal> totalPriceColumn;
    @FXML private TableColumn<Booking, String> bookingDateColumn;
    @FXML private TableColumn<Booking, Void> actionColumn;
    
    private final BookingService bookingService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    @Autowired
    public MyBookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @FXML
    public void initialize() {
        // Configure table columns
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromCity"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toCity"));
        dateColumn.setCellValueFactory(cellData -> 
            javafx.beans.binding.Bindings.createStringBinding(
                () -> cellData.getValue().getTravelDate().format(DATE_FORMATTER)));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("travelTime"));
        busTypeColumn.setCellValueFactory(new PropertyValueFactory<>("busType"));
        passengersColumn.setCellValueFactory(new PropertyValueFactory<>("passengerCount"));
        
        // Configure total price column with currency format
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPriceColumn.setCellFactory(column -> new TableCell<Booking, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("%,.0f VND", price.doubleValue()));
                }
            }
        });
        
        // Configure booking date column
        bookingDateColumn.setCellValueFactory(cellData -> 
            javafx.beans.binding.Bindings.createStringBinding(
                () -> cellData.getValue().getBookingDateTime().format(DATE_TIME_FORMATTER)));
        
        // Configure action column with cancel button
        actionColumn.setCellFactory(column -> new TableCell<Booking, Void>() {
            private final Button cancelButton = new Button("Cancel");
            
            {
                cancelButton.getStyleClass().add("danger-button");
                cancelButton.setOnAction(event -> handleCancelBooking(getTableRow().getItem()));
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(cancelButton);
                }
            }
        });
        
        // Load bookings
        loadBookings();
    }
    
    public void loadBookings() {
        bookingsTable.getItems().setAll(bookingService.getAllBookings());
    }
    
    private void handleCancelBooking(Booking booking) {
        if (booking == null) return;
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Booking");
        alert.setHeaderText("Cancel Booking Confirmation");
        alert.setContentText("Are you sure you want to cancel this booking?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            bookingService.deleteBooking(booking.getId());
            loadBookings();
            
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Booking Cancelled");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Your booking has been cancelled successfully.");
            successAlert.showAndWait();
        }
    }
} 