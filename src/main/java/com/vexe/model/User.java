package com.vexe.model;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // JavaFX Properties
    @Transient
    private StringProperty usernameProperty;
    @Transient
    private StringProperty emailProperty;
    @Transient
    private StringProperty firstNameProperty;
    @Transient
    private StringProperty lastNameProperty;
    @Transient
    private StringProperty phoneNumberProperty;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // JavaFX Property Getters
    public StringProperty usernameProperty() {
        if (usernameProperty == null) {
            usernameProperty = new SimpleStringProperty(username);
        }
        return usernameProperty;
    }

    public StringProperty emailProperty() {
        if (emailProperty == null) {
            emailProperty = new SimpleStringProperty(email);
        }
        return emailProperty;
    }

    public StringProperty firstNameProperty() {
        if (firstNameProperty == null) {
            firstNameProperty = new SimpleStringProperty(firstName);
        }
        return firstNameProperty;
    }

    public StringProperty lastNameProperty() {
        if (lastNameProperty == null) {
            lastNameProperty = new SimpleStringProperty(lastName);
        }
        return lastNameProperty;
    }

    public StringProperty phoneNumberProperty() {
        if (phoneNumberProperty == null) {
            phoneNumberProperty = new SimpleStringProperty(phoneNumber);
        }
        return phoneNumberProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
} 