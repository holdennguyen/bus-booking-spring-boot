package com.vexe.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.Optional;

@Controller
public class MainController {
    
    @FXML private StackPane contentArea;
    @FXML private MenuItem exitMenuItem;
    @FXML private MenuItem aboutMenuItem;
    @FXML private MenuItem appearanceMenuItem;
    @FXML private MenuItem languageMenuItem;
    @FXML private MenuItem notificationsMenuItem;
    @FXML private MenuItem profileMenuItem;
    @FXML private MenuItem userGuideMenuItem;
    @FXML private MenuItem supportMenuItem;
    @FXML private Button dashboardButton;
    @FXML private Button bookTicketButton;
    @FXML private Button myBookingsButton;
    
    private final ApplicationContext applicationContext;
    
    @Autowired
    public MainController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    @FXML
    public void initialize() {
        // Initialize menu items
        exitMenuItem.setOnAction(event -> handleExit());
        aboutMenuItem.setOnAction(event -> showAboutDialog());
        appearanceMenuItem.setOnAction(event -> showAppearanceSettings());
        languageMenuItem.setOnAction(event -> showLanguageSettings());
        notificationsMenuItem.setOnAction(event -> showNotificationSettings());
        profileMenuItem.setOnAction(event -> showProfileSettings());
        userGuideMenuItem.setOnAction(event -> showUserGuide());
        supportMenuItem.setOnAction(event -> showSupport());
        
        // Initialize navigation buttons
        dashboardButton.setOnAction(event -> showDashboard());
        bookTicketButton.setOnAction(event -> showBookingView());
        myBookingsButton.setOnAction(event -> showMyBookingsView());
        
        // Show dashboard by default
        showDashboard();
    }
    
    private void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Any unsaved changes will be lost.");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
    
    private void showAppearanceSettings() {
        showDialog("Appearance Settings", 
                  "Customize the look and feel of your application",
                  "Theme: Light\nFont Size: Medium\nColor Scheme: Default");
    }
    
    private void showLanguageSettings() {
        showDialog("Language Settings",
                  "Choose your preferred language",
                  "Current Language: English\nAvailable Languages:\n- English\n- Vietnamese");
    }
    
    private void showNotificationSettings() {
        showDialog("Notification Settings",
                  "Manage your notification preferences",
                  "Email Notifications: Enabled\nPush Notifications: Disabled\nBooking Reminders: Enabled");
    }
    
    private void showProfileSettings() {
        showDialog("Profile Settings",
                  "Manage your profile information",
                  "Name: User\nEmail: user@example.com\nPhone: Not set");
    }
    
    private void showUserGuide() {
        showDialog("User Guide",
                  "Learn how to use Vexe Bus Booking System",
                  "1. Book a Ticket\n2. View Your Bookings\n3. Manage Profile");
    }
    
    private void showSupport() {
        showDialog("Support",
                  "Get help with Vexe Bus Booking System",
                  "Email: support@vexe.com\nPhone: 1-800-VEXE\nHours: 24/7");
    }
    
    private void showDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public void showDashboard() {
        loadView("/fxml/DashboardView.fxml", "Error loading dashboard view");
    }
    
    public void showBookingView() {
        loadView("/fxml/BookingView.fxml", "Error loading booking view");
    }
    
    public void showMyBookingsView() {
        loadView("/fxml/MyBookingsView.fxml", "Error loading my bookings view");
    }
    
    private void loadView(String fxmlPath, String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(applicationContext::getBean);
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
            showError(errorMessage, e);
        }
    }
    
    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Vexe Bus Booking System");
        alert.setHeaderText(null);
        alert.setContentText("Vexe Bus Booking System\nVersion 1.0\n\nDeveloped by 23DTH1A Group 6");
        alert.showAndWait();
    }
    
    private void showError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message + "\n" + e.getMessage());
        alert.showAndWait();
    }
} 