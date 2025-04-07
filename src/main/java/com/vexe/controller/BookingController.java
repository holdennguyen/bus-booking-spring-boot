package com.vexe.controller;

import com.vexe.model.BusSchedule;
import com.vexe.model.Booking;
import com.vexe.service.BookingService;
import com.vexe.service.BusScheduleService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class BookingController {
    
    @FXML private ComboBox<String> fromCityCombo;
    @FXML private ComboBox<String> toCityCombo;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> busTypeFilter;
    @FXML private Spinner<Integer> passengerCount;
    @FXML private Button searchButton;
    @FXML private TableView<BusSchedule> busTable;
    @FXML private TableColumn<BusSchedule, String> timeColumn;
    @FXML private TableColumn<BusSchedule, String> busTypeColumn;
    @FXML private TableColumn<BusSchedule, Integer> durationColumn;
    @FXML private TableColumn<BusSchedule, Integer> availableSeatsColumn;
    @FXML private TableColumn<BusSchedule, Double> priceColumn;
    @FXML private TableColumn<BusSchedule, String> routeInfoColumn;
    @FXML private TableColumn<BusSchedule, Button> actionColumn;
    @FXML private Text searchStatsText;

    private final BookingService bookingService;
    private final BusScheduleService busScheduleService;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    public BookingController(BookingService bookingService, BusScheduleService busScheduleService) {
        this.bookingService = bookingService;
        this.busScheduleService = busScheduleService;
    }

    @FXML
    public void initialize() {
        // Initialize cities
        fromCityCombo.getItems().addAll(busScheduleService.getAvailableCities());
        toCityCombo.getItems().addAll(busScheduleService.getAvailableCities());
        
        // Initialize bus types
        busTypeFilter.getItems().addAll(busScheduleService.getBusTypes());
        
        // Set default date to tomorrow
        datePicker.setValue(LocalDate.now().plusDays(1));
        
        // Initialize passenger count spinner
        SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        passengerCount.setValueFactory(valueFactory);
        
        // Configure table columns
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        busTypeColumn.setCellValueFactory(new PropertyValueFactory<>("busType"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedDuration"));
        availableSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        routeInfoColumn.setCellValueFactory(new PropertyValueFactory<>("routeInfo"));
        
        // Configure duration column to show hours and minutes
        durationColumn.setCellFactory(column -> new TableCell<BusSchedule, Integer>() {
            @Override
            protected void updateItem(Integer duration, boolean empty) {
                super.updateItem(duration, empty);
                if (empty || duration == null) {
                    setText(null);
                } else {
                    int hours = duration / 60;
                    int minutes = duration % 60;
                    setText(String.format("%dh %02dm", hours, minutes));
                }
            }
        });
        
        // Configure price column to show currency format
        priceColumn.setCellFactory(column -> new TableCell<BusSchedule, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("%,.0f VND", price));
                }
            }
        });
        
        // Configure action column with book buttons
        actionColumn.setCellFactory(column -> new TableCell<BusSchedule, Button>() {
            private final Button bookButton = new Button("Book");
            
            {
                bookButton.getStyleClass().add("primary-button");
                bookButton.setOnAction(event -> handleBooking(getTableRow().getItem()));
            }
            
            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(bookButton);
                }
            }
        });
        
        // Add double-click handler for detailed view
        busTable.setRowFactory(tv -> {
            TableRow<BusSchedule> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    showScheduleDetails(row.getItem());
                }
            });
            return row;
        });
    }

    @FXML
    private void handleSearch() {
        String from = fromCityCombo.getValue();
        String to = toCityCombo.getValue();
        LocalDate date = datePicker.getValue();
        String busType = busTypeFilter.getValue();
        int passengers = passengerCount.getValue();

        if (from == null || to == null || date == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Information", 
                     "Please fill in all required fields before searching.");
            return;
        }

        if (from.equals(to)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Selection", 
                     "Departure and destination cities cannot be the same.");
            return;
        }

        // Fetch schedules from service
        List<BusSchedule> schedules = busScheduleService.findSchedules(from, to, date);
        
        // Apply bus type filter if selected
        if (busType != null && !busType.isEmpty()) {
            schedules = schedules.stream()
                .filter(s -> s.getBusType().equals(busType))
                .toList();
        }
        
        // Update table and stats
        busTable.getItems().clear();
        busTable.getItems().addAll(schedules);
        
        // Update search stats
        updateSearchStats(schedules);
    }
    
    private void updateSearchStats(List<BusSchedule> schedules) {
        if (schedules.isEmpty()) {
            searchStatsText.setText("No schedules found for the selected criteria.");
        } else {
            String stats = String.format("Found %d schedule(s) • Price range: %,.0f - %,.0f VND",
                schedules.size(),
                schedules.stream().mapToDouble(BusSchedule::getPrice).min().orElse(0),
                schedules.stream().mapToDouble(BusSchedule::getPrice).max().orElse(0)
            );
            searchStatsText.setText(stats);
        }
    }
    
    private void showScheduleDetails(BusSchedule schedule) {
        String details = String.format("""
            Time: %s
            From: %s
            To: %s
            Bus Type: %s
            Available Seats: %d
            Price: %.2f VND
            Route Info: %s
            Amenities:
            %s
            WiFi: %s
            USB Power: %s
            """,
            schedule.getTime(),
            schedule.getFromCity(),
            schedule.getToCity(),
            schedule.getBusType(),
            schedule.getAvailableSeats(),
            schedule.getPrice(),
            schedule.getRouteInfo(),
            String.join("\n", schedule.getAmenities()),
            schedule.hasAmenity("WiFi") ? "Yes" : "No",
            schedule.hasAmenity("USB Power") ? "Yes" : "No"
        );
        
        showAlert(Alert.AlertType.INFORMATION, "Schedule Details", details);
    }

    private void handleBooking(BusSchedule schedule) {
        if (schedule == null) return;
        
        try {
            // Parse the time string to LocalTime
            LocalTime travelTime = LocalTime.parse(schedule.getTime(), timeFormatter);
            
            // Calculate total price using BigDecimal
            BigDecimal totalPrice = BigDecimal.valueOf(schedule.getPrice())
                .multiply(BigDecimal.valueOf(passengerCount.getValue()));
            
            // Create and save the booking
            Booking booking = new Booking(
                fromCityCombo.getValue(),
                toCityCombo.getValue(),
                datePicker.getValue(),
                travelTime,
                schedule.getBusType(),
                passengerCount.getValue(),
                totalPrice
            );
            
            bookingService.saveBooking(booking);
            
            String message = String.format("""
                Booking Confirmed!
                
                From: %s
                To: %s
                Date: %s
                Time: %s
                Bus Type: %s
                Passengers: %d
                Total Price: %,.0f VND
                
                Route Info: %s
                Duration: %d hours %d minutes
                """,
                booking.getFromCity(),
                booking.getToCity(),
                booking.getTravelDate(),
                booking.getTravelTime().format(timeFormatter),
                booking.getBusType(),
                booking.getPassengerCount(),
                booking.getTotalPrice().doubleValue(),
                schedule.getRouteInfo(),
                schedule.getEstimatedDuration() / 60,
                schedule.getEstimatedDuration() % 60
            );
            
            showAlert(Alert.AlertType.INFORMATION, "Booking Confirmation", message);
            
            // Clear the form
            fromCityCombo.setValue(null);
            toCityCombo.setValue(null);
            datePicker.setValue(LocalDate.now().plusDays(1));
            busTypeFilter.setValue(null);
            passengerCount.getValueFactory().setValue(1);
            busTable.getItems().clear();
            searchStatsText.setText("");
            
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Booking Error", 
                     "An error occurred while processing your booking. Please try again.");
        }
    }
    
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 