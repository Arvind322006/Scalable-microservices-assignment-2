package com.arvind.aitutoring.student.dto;

import com.arvind.aitutoring.student.entity.Student;
import java.time.LocalDateTime;

public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private String subscriptionStatus;
    private String tier;
    private LocalDateTime createdAt;

    public StudentResponse() {
    }

    public StudentResponse(Long id, String name, String email, String subscriptionStatus, String tier, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subscriptionStatus = subscriptionStatus;
        this.tier = tier;
        this.createdAt = createdAt;
    }

    public static StudentResponse fromEntity(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getSubscriptionStatus(),
                student.getTier(),
                student.getCreatedAt()
        );
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
