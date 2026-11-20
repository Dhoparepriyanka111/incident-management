
package com.example.incident_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Incident title validation
    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title cannot exceed 150 characters")
    @Column(nullable = false, length = 150)
    private String title;

    // Incident description validation
    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    @Column(nullable = false, length = 2000)
    private String description;

    // Category validation
    @NotBlank(message = "Category is required")
    @Column(nullable = false, length = 100)
    private String category;

    // Priority validation
    @NotBlank(message = "Priority is required")
    @Pattern(
        regexp = "LOW|MEDIUM|HIGH",
        message = "Priority must be LOW, MEDIUM or HIGH"
    )
    @Column(nullable = false, length = 20)
    private String priority;

    // Status validation
    @NotBlank(message = "Status is required")
    @Pattern(
        regexp = "NEW|IN_PROGRESS|RESOLVED|CLOSED",
        message = "Status must be NEW, IN_PROGRESS, RESOLVED or CLOSED"
    )
    @Column(nullable = false, length = 30)
    private String status;

    // Reporter details validation
    @NotBlank(message = "Reporter name is required")
    @Column(nullable = false, length = 100)
    private String reporterName;

    @NotBlank(message = "Reporter email is required")
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false, length = 150)
    private String reporterEmail;

    // Assigned team
    @Column(length = 100)
    private String assignedTo;

    // Date and time fields
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Default constructor
    public Incident() {
    }

    // Automatically set timestamps when creating an incident
    @PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;

        if (status == null || status.isBlank()) {
            status = "NEW";
        }
    }

    // Automatically update updatedAt when modifying an incident
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
