package com.arvind.aitutoring.student.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String subscriptionStatus; // INACTIVE, ACTIVE, CANCELLED

    @Column(nullable = false)
    private String tier; // FREE, PREMIUM, PRO

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Student() {
    }

    public Student(String name, String email, String subscriptionStatus, String tier) {
        this.name = name;
        this.email = email;
        this.subscriptionStatus = subscriptionStatus;
        this.tier = tier;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.subscriptionStatus == null) {
            this.subscriptionStatus = "INACTIVE";
        }
        if (this.tier == null) {
            this.tier = "FREE";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
