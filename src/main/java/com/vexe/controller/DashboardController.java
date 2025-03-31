package com.vexe.controller;

import com.vexe.model.Booking;
import com.vexe.service.BookingService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {
    
    @FXML private Label totalBookingsLabel;
    @FXML private Label todayBookingsLabel;
    @FXML private Label popularRouteLabel;
    
    @FXML private TableView<Booking> recentBookingsTable;
    @FXML private TableColumn<Booking, String> dateColumn;
    @FXML private TableColumn<Booking, String> fromColumn;
    @FXML private TableColumn<Booking, String> toColumn;
    @FXML private TableColumn<Booking, String> busTypeColumn;
    @FXML private TableColumn<Booking, Double> priceColumn;
    
    private final BookingService bookingService;
    private final MainController mainController;
    
    @Autowired
    public DashboardController(BookingService bookingService, MainController mainController) {
        this.bookingService = bookingService;
        this.mainController = mainController;
    }
    
    @FXML
    public void initialize() {
        setupTableColumns();
        loadDashboardData();
    }
    
    private void setupTableColumns() {
        dateColumn.setCellValueFactory(data -> data.getValue().getBookingDateTimeProperty());
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromCity"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toCity"));
        busTypeColumn.setCellValueFactory(new PropertyValueFactory<>("busType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        
        // Format date column
        dateColumn.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(item);
                        setText(formatter.format(dateTime));
                    } catch (Exception e) {
                        setText(item);
                    }
                }
            }
        });
    }
    
    private void loadDashboardData() {
        // Load total bookings
        long totalBookings = bookingService.getTotalBookings();
        totalBookingsLabel.setText(String.valueOf(totalBookings));
        
        // Load today's bookings
        long todayBookings = bookingService.getTodayBookings();
        todayBookingsLabel.setText(String.valueOf(todayBookings));
        
        // Load popular route
        Map.Entry<String, Long> popularRoute = bookingService.getMostPopularRoute();
        if (popularRoute != null) {
            popularRouteLabel.setText(popularRoute.getKey());
        }
        
        // Load recent bookings
        List<Booking> recentBookings = bookingService.getRecentBookings(5);
        recentBookingsTable.getItems().setAll(recentBookings);
    }
    
    @FXML
    private void handleNewBooking() {
        mainController.showBookingView();
    }
    
    @FXML
    private void handleViewAllBookings() {
        mainController.showMyBookingsView();
    }
} 